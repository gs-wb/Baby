package com.yikang.health.net;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.util.Base64;

public class HttpHelper {

	/**
	 * 通过POST方式连接并获取数据
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String requestToServerWithPost(String url, String json)
			throws Exception {
		return requestToServerWithPost(url, json, 10000);
	}

	/**
	 * 通过POST方式连接并获取数据
	 * 
	 * @param url
	 * @param json
	 * @param time
	 *            超时时间
	 * @return
	 * @throws Exception
	 */
	public static String requestToServerWithPost(String url, String json,
			int time) throws Exception {
		String responseString = null;
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, time);
		HttpPost post = new HttpPost(url);
		StringEntity s = new StringEntity(json);
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json");
		post.setEntity(s);
		HttpResponse response = httpClient.execute(post);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			responseString = EntityUtils.toString(response.getEntity());
		} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
			throw new Exception("网络异常");
		}
		return convert(responseString);
//		return responseString;
	}
	public static String convert(String utfString){  
	    StringBuilder sb = new StringBuilder();  
	    int i = -1;  
	    int pos = 0;  
	      
	    while((i=utfString.indexOf("\\u", pos)) != -1){  
	        sb.append(utfString.substring(pos, i));  
	        if(i+5 < utfString.length()){  
	            pos = i+6;  
	            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	        }  
	    }  
	    sb.append(utfString.substring(pos)); 
	    return sb.toString();  
	}  
}
