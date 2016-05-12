package com.yikang.health.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

import org.apache.http.message.BasicNameValuePair;


public class WebDataUtils {

	private static WebDataUtils requestUtils;
	
	static {
		requestUtils = new WebDataUtils();
	}

	public static WebDataUtils getInstance(){
		return requestUtils==null? new WebDataUtils():requestUtils;
	} 
	
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
	
	public String getWeatherJson(HashMap<String, String> hashMap,String httpUrl){
		String httpArg = "";
		if(hashMap!=null){
			Set<Map.Entry<String, String>> set = hashMap.entrySet();
			Iterator<Map.Entry<String, String>> ite = set.iterator();
			while(ite.hasNext()){
				Map.Entry<String, String> entry = ite.next();
				String key = entry.getKey();
				String value = entry.getValue();
				httpArg += key+"="+value +"&";
			}
		}
		return CallDataHelper.getInstance().requestWeather(httpUrl, httpArg.endsWith("&")?httpArg.substring(0, httpArg.length()-1):httpArg);
	}
	
}
