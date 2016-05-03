package com.yikang.health.interfaces;

import java.io.File;

/**
 * 磁盘缓存接口定义
 * @author SP-ShenWeiliang
 */
public interface IDiscCacheAware {

	void put(File file);
	
	File get(File dir, String key);
	
	void remove(File file);
	
	void clear();
}
