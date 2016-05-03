package com.yikang.health.net;

import java.lang.Thread.UncaughtExceptionHandler;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.widget.Toast;

import com.yikang.health.YIKApplication;
import com.yikang.health.cache.LogUtils;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskCompleteListener;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.utils.Utils;

/**
 * 网络请求管理类
 * @author zwb
 */

public class NetTaskManager {

	private Map<Integer, BasePostHttpTask> taskPool;
	private volatile static NetTaskManager instance;
	private BlockingQueue<Runnable> workQueue;
	private Executor threadPoolExecutor;

	private static final int TOTALTIMEOUT = Constants.CONNECTTIMEOUT + 15 * 1000;
	private Set<String> mCookie;
	private String sessionID;

	private NetTaskManager() {
		taskPool = Collections
				.synchronizedMap(new HashMap<Integer, BasePostHttpTask>());
		int minCorePoolSize = 20;
		int maxCorePoolSize = 100;
		long keepAliveTime = 0L;

		workQueue = new LinkedBlockingQueue<Runnable>(maxCorePoolSize);
		threadPoolExecutor = new ThreadPoolExecutor(minCorePoolSize, maxCorePoolSize,
				keepAliveTime, TimeUnit.SECONDS, workQueue, new ThreadFactory() {  
                    final AtomicInteger threadNumber = new AtomicInteger(1);  
  
                    public Thread newThread(Runnable r) {  
                        Thread t = new Thread(Thread.currentThread().getThreadGroup(), r, "topPatternTasklet-thread"  
                                + (threadNumber.getAndIncrement()));  
                        t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {  
  
                            public void uncaughtException(Thread t, Throwable e) {  
                                System.out.println(e);  
                            }  
                              
                        });  
                        return t;  
                    }  
                }, new ThreadPoolExecutor.CallerRunsPolicy());
		mCookie = new HashSet<String>();
	}

	public static NetTaskManager getTaskPool() {
		if (null == instance) {
			synchronized (NetTaskManager.class) {
				if (null == instance) {
					instance = new NetTaskManager();
				}
			}
		}
		return instance;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void clearCookie() {
		if (mCookie == null)
			return;

		synchronized (mCookie) {
			mCookie.clear();
			sessionID = null;
		}
	}

	public void addNewTask(Context context, final int connId, final String url,
			TaskCompleteListener listener, final String msg,
			Object... params) {
		addNewTask(context, connId, url, true, listener, msg, params);
	}

	@SuppressLint("NewApi")
	public void addNewTask(Context context, final int connId, final String url,
			final boolean showProgress, TaskCompleteListener listener,
			final String msg, Object... params) {

		cancelTask(connId);

		BasePostHttpTask mTask = new BasePostHttpTask(context, connId, url) {
			@Override
			protected boolean isShowEProgressDialog() {
				return showProgress;
			}

			@Override
			protected String getWaitMessage() {
				return msg == null ? "" : msg;
			}
		};

		mTask.setTaskCompleteListener(listener);

		taskPool.put(connId, mTask);

		if (Utils.hasHoneycomb())
			mTask.executeOnExecutor(threadPoolExecutor, params);
		else
			mTask.execute(params);

		Handler handler = new Handler(context.getMainLooper());
		handler.postDelayed(new InterruptTaskRunnable(mTask, connId, url,
				listener), TOTALTIMEOUT);
	}

	public void addNewTaskOnUIThread(final Context context, final int connId,
			final String url, final TaskCompleteListener listener,
			final Object... params) {

		if (context == null) {
			throw new InvalidParameterException("Context can't be null");
		}

		if (context instanceof ContextWrapper) {
			if (((ContextWrapper) context).getBaseContext() == null)
				throw new InvalidParameterException(
						"Context base can't be null");
		}

		final Handler handler = new Handler(context.getMainLooper());
		handler.post(new Runnable() {

			@Override
			public void run() {

				cancelTask(connId);

				BasePostHttpTask mTask = new BasePostHttpTask(context, connId,
						url) {
				};

				mTask.setTaskCompleteListener(listener);

				taskPool.put(connId, mTask);

				if (Utils.hasHoneycomb())
					mTask.executeOnExecutor(threadPoolExecutor, params);
				else
					mTask.execute(params);

				handler.postDelayed(new InterruptTaskRunnable(mTask, connId,
						url, listener), TOTALTIMEOUT);
			}
		});
	}

	public boolean getIsRunningTask(int connId) {
		return taskPool.containsKey(connId);
	}

	public void removeTask(int connId) {
		taskPool.remove(connId);
	}

	public void cancelTask(int connId) {
		if (taskPool.containsKey(connId)) {
			taskPool.get(connId).cancelTask(false);
			taskPool.remove(connId);
		}
	}

	public void cancelAllTasks() {
		if (taskPool != null) {
			Set<Entry<Integer, BasePostHttpTask>> entries = taskPool.entrySet();
			for (Entry<Integer, BasePostHttpTask> entry : entries) {
				entry.getValue().cancelTask(false);
			}
			taskPool.clear();
		}
		clearCookie();
	}

	public void cancelAllTasks(int exceptId) {
		if (taskPool != null) {
			Set<Entry<Integer, BasePostHttpTask>> entries = taskPool.entrySet();
			for (Entry<Integer, BasePostHttpTask> entry : entries) {
				if (entry.getKey() != exceptId) {
					entry.getValue().cancelTask(false);
				}
			}
			taskPool.clear();
		}
	}

	class InterruptTaskRunnable implements Runnable {

		private BasePostHttpTask mTask;
		private int mId;
		private String mUrl;
		private TaskCompleteListener mTaskListener;

		public InterruptTaskRunnable(BasePostHttpTask task, int connId,
				String url, TaskCompleteListener listener) {
			this.mTask = task;
			this.mId = connId;
			this.mUrl = url;
			this.mTaskListener = listener;
		}

		@Override
		public void run() {
			if (mTask != null && !mTask.isCanceled && !mTask.isDone
					&& mTask.nextResult() == null) {
				cancelTask(mId);
				LogUtils.d("the request task is interrupted:" + mUrl);

				if (mTaskListener != null
						&& mTaskListener instanceof TaskExpandListener) {
					((TaskExpandListener) mTaskListener)
							.onTaskError(
									"0",
									mId,
									ServerConnect
											.getResultInfo(Constants.STATUS_NETWORK_TIMEOUT));
				} else {
					Toast.makeText(
							YIKApplication.getContext(),
							ServerConnect
									.getResultInfo(Constants.STATUS_NETWORK_TIMEOUT),
							Toast.LENGTH_LONG).show();
				}

				mTask = null;
			}

		}
	}
}
