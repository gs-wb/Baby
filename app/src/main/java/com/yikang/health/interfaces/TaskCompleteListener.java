package com.yikang.health.interfaces;

/**
 * 网络请求回调接口
 * @author hh
 *
 */
public interface TaskCompleteListener {

	/**
	 * 
	 * @param server返回状态码
	 * @param 返回结果(泛型),可根据需求自定义，默认的为json String
	 * @param 请求的操作代码
	 */
	<T> void onTaskCompleted(String resultCode, T result, int connId);
}