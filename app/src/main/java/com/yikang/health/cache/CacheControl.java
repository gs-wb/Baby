package com.yikang.health.cache;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import android.graphics.Bitmap;

import com.yikang.health.interfaces.IDiscCacheAware;

/**
 * 缓存控制类
 *
 */
public final class CacheControl {
	
	IDiscCacheAware discCache;
	WeakReference<Bitmap> weakBitmap;
	SoftReference<Bitmap> softBitmap;
	WeakHashMap<String, Object> weakMap;
	private volatile static CacheControl instance;
	
	public static CacheControl getInstance() {
		if (instance == null) {
			synchronized (CacheControl.class) {
				instance = new CacheControl();
			}
		}
		return instance;
	}
	
	private CacheControl() {
		init();
	};
	
	public void init() {
		discCache = new BaseDiscCache(StorageUtils.getCacheDirectory(StorageUtils.FILE_TYPE.ROOT));
	}

}
