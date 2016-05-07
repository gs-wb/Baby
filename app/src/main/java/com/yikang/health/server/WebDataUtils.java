package com.yikang.health.server;

import java.util.HashMap;

import android.text.TextUtils;


public class WebDataUtils {

	private static WebDataUtils requestUtils;
	
	static {
		requestUtils = new WebDataUtils();
	}

	public static WebDataUtils getInstance(){
		return requestUtils==null? new WebDataUtils():requestUtils;
	} 
	
	
//	public String isSuccess(String orgJson){
//
//		if(TextUtils.isEmpty(orgJson))return null;
//
//		OrgResultInfo resultInfo = GsonTools.getObject(orgJson, OrgResultInfo.class);
//		if(resultInfo==null) return null;
//		else {
//			if(TextUtils.equals("0", resultInfo.getError())) return resultInfo.getData();
//		}
//		return null;
//	}
	
	
	/**
	 * 请求网络数据
	 * @param hashMap
	 * @return
	 */
	public String jsonDataStr(HashMap<String, String> hashMap,String methodName){
		
		IDataRequestServer requestServer = HttpRequestServer.getInstance();
		String resultJson = requestServer.requestWebData("http://120.76.136.195/user/"+methodName, hashMap);
		return resultJson;
	}
	
	
	
}
