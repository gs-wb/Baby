package com.yikang.health.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.message.BasicNameValuePair;

public class HttpRequestServer implements IDataRequestServer{

	private static IDataRequestServer requestServer = null;
	static {
		requestServer = new HttpRequestServer();
	}
	
	public static IDataRequestServer getInstance(){
		return requestServer==null?new HttpRequestServer():requestServer;
	}
	
	public String requestWebData(String requestUrl,HashMap<String, String> hashMap) {
		
		List<BasicNameValuePair> listPamars = null;
		if(hashMap!=null){
			listPamars = new ArrayList<BasicNameValuePair>();
			Set<Entry<String, String>>  set = hashMap.entrySet();
			Iterator<Entry<String, String>> ite = set.iterator();
			while(ite.hasNext())
			{
				Entry<String, String> entry = ite.next();
				String key = entry.getKey();
				String value = entry.getValue();
				listPamars.add(new BasicNameValuePair(key,value==null?"":value));
			}
		}
		
		return CallDataHelper.getInstance().requestData(requestUrl, listPamars);
	}
	
	
	
}
