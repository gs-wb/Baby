package com.yikang.health.utils;

import com.yikang.health.model.BaseModel;

import java.util.Comparator;

public class MyComparator implements Comparator {
	public int compare(Object obj1, Object obj2) {
		BaseModel t1 = (BaseModel) obj1;
		BaseModel t2 = (BaseModel) obj2;
		if (Integer.parseInt(t1.getId()) > Integer.parseInt(t2.getId())) {
			return 1;
		} else if (Integer.parseInt(t1.getId()) < Integer.parseInt(t2.getId())) {
			return -1;
		} else {
			// 利用String自身的排序方法。
			// 如果步数相同就按名字进行排序
			return t1.getId().compareTo(t2.getId());
		}

	}
}