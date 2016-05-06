package com.yikang.health.server;

import java.util.HashMap;

public interface IDataRequestServer {
	
	/**
	 * 
	 * @param requestUrl 请求数据的URL
	 * @param hashMap 请求数据的参数对象
	 * @return
	 */
	public String requestWebData(String requestUrl, HashMap<String, String> hashMap);
	
}
