package com.yikang.health.cache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.yikang.health.cache.StorageUtils.FILE_TYPE;
/**
 * 日志工具类
 * @author hh
 *
 */
public class LogUtils {
	private final static String TAG = LogUtils.class.getSimpleName();

	public static final String ROOT_PATH = StorageUtils
			.getCacheDirectory(FILE_TYPE.LOG) + File.separator;

	private static ReentrantLock fileLock;
	private static Application mApplication;
	public static boolean LOG_ENABLED = true;  //日志开关

	public static void init(Application application) {
		if (mApplication != null) {
			return;
		}
		mApplication = application;
		fileLock = new ReentrantLock();
		Properties props = new Properties();
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties");
			props.load(in);
		} catch (Exception e) {
			// ignore
		}

		String buildMode = props.getProperty("buildmode", "");
		if (!TextUtils.isEmpty(buildMode)) {
			LOG_ENABLED = buildMode.equals("0") ? true : false;
		} else {
			LOG_ENABLED = false;
		}
	}

	public static boolean isLoggable(int level) {
		return Log.isLoggable(TAG, level);
	}

	public static String getStackTraceString(Throwable th) {
		return Log.getStackTraceString(th);
	}

	public static void printStackTrace(String description, Throwable th) {
		if (LOG_ENABLED)
			Log.e(TAG, description + "\n" + getStackTraceString(th));
	}

	public static void v(String msg) {
		if (LOG_ENABLED)
			Log.v(TAG, msg);
	}

	public static void v(String msg, Throwable th) {
		if (LOG_ENABLED)
			Log.v(TAG, msg, th);
	}

	public static void d(String msg) {
		if (LOG_ENABLED)
			Log.d(TAG, msg);
	}

	public static void d(String msg, Throwable th) {
		if (LOG_ENABLED)
			Log.d(TAG, msg, th);
	}

	public static void i(String msg) {
		if (LOG_ENABLED)
			Log.i(TAG, msg);
	}

	public static void i(String msg, Throwable th) {
		if (LOG_ENABLED)
			Log.i(TAG, msg, th);
	}

	public static void w(String msg) {
		if (LOG_ENABLED)
			Log.w(TAG, msg);
	}

	public static void w(String msg, Throwable th) {
		if (LOG_ENABLED)
			Log.w(TAG, msg, th);
	}

	public static void w(Throwable th) {
		if (LOG_ENABLED)
			Log.w(TAG, th);
	}

	public static void e(String msg) {
		if (LOG_ENABLED)
			Log.w(TAG, msg);
	}

	public static void e(String msg, Throwable th) {
		if (LOG_ENABLED)
			Log.e(TAG, msg, th);
	}

	/**
	 * 读取日志文件
	 * 
	 * @param fullName
	 * @return
	 */
	public static String readFileToString(String fileName) {
		StringBuffer sbuffer = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(ROOT_PATH
					+ fileName));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sbuffer.append(line + "\n");
			}
		} catch (Exception e) {
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sbuffer.toString();
	}

	/**
	 * 写入日志文件
	 * 
	 * @param data
	 */
	public static void writeFile(String fileName, String data) {
		BufferedWriter buf = null;
		try {
			fileLock.lock();
			buf = new BufferedWriter(new FileWriter(ROOT_PATH + fileName, true));
			buf.write(data, 0, data.length());
			buf.newLine();
		} catch (Exception e) {
			LogUtils.printStackTrace("An error occurred when call the method {LotteryLog->writeFile()}",e);
		} finally {
			try {
				buf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileLock.unlock();
		}
	}

	/**
	 * 删除日志文件
	 * 
	 * @param fullFileName
	 */
	public static void deleteFile(String fileName) {
		try {
			fileLock.lock();
			File file = new File(ROOT_PATH + fileName);
			if (file != null && file.exists()) {
				file.delete();
			}
		} finally {
			fileLock.unlock();
		}

	}
}
