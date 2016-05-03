package com.yikang.health.cache;

import java.io.File;

import android.os.Environment;
import android.text.TextUtils;

import com.yikang.health.YIKApplication;
import com.yikang.health.interfaces.IDiscCacheAware;

/**
 * 存储路径获取工具类
 * 
 * @author hh
 */
public final class StorageUtils {

	private static final String DIR_ROOT = "PeaceOfHouse";

	private static final String DIR_AVATAR = "avatar";

	private static final String DIR_IMAGE = "image";

	private static final String DIR_VOICE = "voice";

	private static final String DIR_EMOJI = "emoji";

	private static final String DIR_LOG = "log";

	private static final String DIR_DOWNLOAD = "download";

	private static final String DIR_CAMERA = "camera";
	
	private static final String DIR_ACCOUNTAVATAR = "avatar";
	
	private static final String DIR_APPCENTER = "appcenter";

	public static enum FILE_TYPE {
		ROOT, AVATAR, IMAGE, VOICE, EMOJI, LOG, DOWNLOAD, CAMERA, ACCOUNTAVATAR, APPCENTER
	};

	/**
	 * 根据存储文件的类型获取对应的存储路径
	 * 
	 * @param type
	 * @return
	 */
	public static File getCacheDirectory(FILE_TYPE type) {
		File rootDir = null;
		String folderName = "";
		boolean isUserOwn = true;
		
		if(FILE_TYPE.ROOT.compareTo(type) == 0){
			return getRootDirectory();
		}

		switch (type) {
		case ROOT:
			break;
		case AVATAR:
			folderName = DIR_AVATAR;
			break;
		case IMAGE:
			folderName = DIR_IMAGE;
			break;
		case VOICE:
			folderName = DIR_VOICE;
			break;
		case EMOJI:
			folderName = DIR_EMOJI;
			break;
		case LOG:
			isUserOwn = false;
			folderName = DIR_LOG;
			break;
		case DOWNLOAD:
			isUserOwn = false;
			folderName = DIR_DOWNLOAD;
			break;
		case CAMERA:
			isUserOwn = false;
			folderName = DIR_CAMERA;
			break;
		case ACCOUNTAVATAR:
			isUserOwn = false;
			folderName = DIR_ACCOUNTAVATAR;
			break;
		case APPCENTER:
			isUserOwn = false;
			folderName = DIR_APPCENTER;
			break;
		default:
			break;
		}

		if (!isUserOwn) {
			rootDir = getRootDirectory();
		} else {
			rootDir = getUserOwnDirectory();
		}
		
		if (!TextUtils.isEmpty(folderName)) {
			File reserveCacheDir = new File(rootDir, folderName);
			if (!reserveCacheDir.exists()) {
				if (!reserveCacheDir.mkdir()) {
					reserveCacheDir = rootDir;
				}
			}
			return reserveCacheDir;
		}
		return rootDir;
	}
	
	private synchronized static File getUserOwnDirectory() {
		String key = null;
//		if (DataCache.getDataCache().getUser() == null) {
//			User currentUser = CommunityDBManager.getInstance()
//					.getRecentlyUser();
//			if (currentUser != null) {
//				key = currentUser.getUid();
//			}
//		} else {
//			key = DataCache.getDataCache().getUser().getUid();
//		}
		
		if (TextUtils.isEmpty(key)) {
			LogUtils.e("UserID is necessary while create cache directory of user！");
			throw new IllegalArgumentException();
		}

		File dir = new File(getRootDirectory(),
				Md5FileNameGenerator.generate(key));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		return dir;
	}

	/**
	 * 获取存储路径
	 * 
	 * @param context
	 * @param type
	 * @return
	 */
	private static File getRootDirectory() {
		File rootDir = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			rootDir = getExternalCacheDir();
		}

		if (rootDir == null) {
			rootDir = YIKApplication.getContext().getCacheDir();
		}

		return rootDir;
	}

	/**
	 * 获取外部存储介质(SD卡)上的缓存路径
	 * 
	 * @return
	 */
	private synchronized static File getExternalCacheDir() {
		File appCacheDir = new File(Environment.getExternalStorageDirectory(),
				DIR_ROOT);
		if (!appCacheDir.exists()) {
			if (!appCacheDir.mkdirs()) {
				return null;
			}
		}
		return appCacheDir;
	}

	/**
	 * 读取文件
	 * @param key
	 * @return
	 */
	public synchronized static File getFile(FILE_TYPE type, String fileName) {
		IDiscCacheAware discCache = CacheControl.getInstance().discCache;
		String name = Md5FileNameGenerator.generate(fileName);
		File imageFile = discCache.get(getCacheDirectory(type), name);
		return imageFile.exists() ? imageFile : null;
	}

	/**
	 * 删除文件
	 * @param uri
	 */
	public static void removeFile(FILE_TYPE type, String fileName) {
		File file = getFile(type, fileName);
		if (file != null) {
			CacheControl.getInstance().discCache.remove(file);
		}
	}

	/**
	 * 获取语音文件
	 * 
	 * @param key
	 * @return
	 */
	public synchronized static File getVoice(String key) {
		IDiscCacheAware discCache = CacheControl.getInstance().discCache;
		String fileName = Md5FileNameGenerator.generate(key);
		File mediaFile = discCache.get(getCacheDirectory(FILE_TYPE.VOICE),
				fileName);
		return mediaFile.exists() ? mediaFile : null;
	}

	/**
	 * 清除所有文件
	 */
	public static void clearFiles(final File dir) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				File[] cachedFiles = dir.listFiles();
				if (cachedFiles != null) {
					for (File cachedFile : cachedFiles) {
						if (cachedFile != null)
							cachedFile.delete();
					}
				}
			}
		}).start();
	}
}
