package com.yikang.health.interfaces;

/**
 * 网络请求中止回调接口
 * @author hh
 *
 */
public interface TaskExpandListener extends TaskCompleteListener {
	void onTaskCanceled();

	void onTaskError(String resultCode, int conId, String msg);
}
