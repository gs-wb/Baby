package com.yikang.health.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.yikang.health.R;
import com.yikang.health.constant.Constants;
/**
 * APP下载service
 * @author hh
 * 
 */
public class UpdateAppService extends Service implements Runnable {
	protected int notif_id = 10000;
	// 文件存储
	private File updateDir = null;
	private File updateFile = null;
	private RemoteViews remView;
	// 下载状态
	private final static int DOWNLOAD_PROGRESS = 1;
	private final static int DOWNLOAD_COMPLETE = 2;
	private final static int DOWNLOAD_FAIL = 3;
	// 通知栏
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 创建文件
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			updateDir = new File(Environment.getExternalStorageDirectory(),
					"qpnews/download/");
			updateFile = new File(updateDir.getPath(), getResources()
					.getString(R.string.apk_download_name));
		}
		this.updateNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		this.updateNotification = new Notification();
		updateNotification.icon = R.drawable.ic_launcher;
		updateNotification.contentIntent = PendingIntent.getService(this, 0,
				new Intent(), 0);
		remView = new RemoteViews(this.getPackageName(),
				R.layout.notified_update);
		remView.setImageViewResource(R.id.notific_ico, R.drawable.ic_launcher);
		remView.setTextViewText(R.id.notific_txt,
				getResources().getString(R.string.apk_start_download));
		remView.setProgressBar(R.id.notific_pb, 100, 0, false);
		updateNotification.contentView = remView;
		// 发出通知
		updateNotificationManager.notify(notif_id, updateNotification);
		new Thread(this).start();// 下载
	}

	public UpdateAppService() {
		// TODO Auto-generated constructor stub
		super();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_PROGRESS:
				updateProgress(msg.arg1, msg.arg2);
				break;
			case DOWNLOAD_COMPLETE:
				updateSuccess();
				break;
			case DOWNLOAD_FAIL:
				updateError();
				break;
			default:
				break;
			}
		}
	};

	private void updateProgress(int total, int length) {
		int tempInt = (int) (length * 100.0f / total);
		remView.setTextViewText(R.id.notific_txt, String.format(getResources()
				.getString(R.string.apk_download_progress), tempInt));
		remView.setProgressBar(R.id.notific_pb, 100, tempInt, false);
		updateNotificationManager.notify(notif_id, updateNotification);
	}

	private void updateError() {
		Toast.makeText(this,
				getResources().getString(R.string.apk_download_faile),
				Toast.LENGTH_SHORT).show();
		updateNotificationManager.cancel(notif_id);
		stopSelf();
	}

	private void updateSuccess() {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(updateFile),
				"application/vnd.android.package-archive");
		updateNotificationManager.cancel(notif_id);
		startActivity(intent);
		stopSelf();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(Constants.APP_DOWNLOAD_URL))
			return;
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpUriRequest request = new HttpGet(Constants.APP_DOWNLOAD_URL);
			HttpResponse rsp = client.execute(request);
			InputStream inputStream = rsp.getEntity().getContent();

			FileOutputStream fos = null;
			try {
				if (!updateDir.exists()) {
					updateDir.mkdirs();
				}
				if (!updateFile.exists()) {
					updateFile.createNewFile();
				}
				fos = new FileOutputStream(updateFile);
				byte[] buf = new byte[1024];
				int count = 0;
				int len;
				int length = 0;
				try {
					String contentLength = String.valueOf(rsp.getEntity()
							.getContentLength());
					if (contentLength != null) {
						length = Integer.parseInt(contentLength);
					}
				} catch (Exception e) {
				}
				long t = System.currentTimeMillis();
				while ((len = inputStream.read(buf, 0, 1024)) != -1) {
					Message msg = new Message();
					fos.write(buf, 0, len);
					fos.flush();
					count += len;
					if (System.currentTimeMillis() - t > 500) {
						msg.what = DOWNLOAD_PROGRESS;
						msg.arg1 = length;
						msg.arg2 = count;
						handler.sendMessage(msg);
						t = System.currentTimeMillis();
					}
				}
				if (length == count)
					handler.sendEmptyMessage(DOWNLOAD_COMPLETE);
			} finally {
				if (fos != null) {
					fos.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			}
		} catch (Exception e) {
			handler.sendEmptyMessage(DOWNLOAD_FAIL);
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
