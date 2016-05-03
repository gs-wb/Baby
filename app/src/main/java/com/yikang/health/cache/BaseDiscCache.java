package com.yikang.health.cache;

import java.io.File;

import com.yikang.health.interfaces.IDiscCacheAware;

/**
 * 磁盘缓存类
 * @version 1.0
 */
public class BaseDiscCache implements IDiscCacheAware {

	protected File cacheDir;
	
	public BaseDiscCache(File dir) {
		if (dir == null) {
			throw new IllegalArgumentException("BaseDiscCache:argument must be not null");
		}
		this.cacheDir = dir;
	}
	
	@Override
	public void put(File file) {
		// do nothing
	}
	
	@Override
	public File get(File dir, String key) {
		return new File(dir, key);
	}
	
	@Override
	public void remove(File file) {
		if (file != null && file.exists()) {
			file.delete();
		}
	}

	@Override
	public void clear() {
		File[] files = cacheDir.listFiles();
		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}
	}
}
