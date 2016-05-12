package com.yikang.health.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class CallDataHelper {

	private static CallDataHelper callHelper = new CallDataHelper();

	public static CallDataHelper getInstance() {
		return callHelper == null ?new CallDataHelper():callHelper;
	}

	/**
	 * 天气接口
	 * @param httpUrl
	 * @param httpArg
	 * @return
	 */
	public String requestWeather(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey",  "9cb6019d0dbf074445e4b4f21f989784");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String requestData(String requestUrl,List<BasicNameValuePair> listPamars){
		String dataStr="";
		try {
			HttpPost request = new HttpPost(requestUrl); // 根据内容来源地址创建一个Http请求
			
			if(listPamars!=null){
				request.setEntity((HttpEntity) new UrlEncodedFormEntity(listPamars, HTTP.UTF_8));// 设置参数的编码
			}
			
			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			if (httpResponse.getStatusLine().getStatusCode() != 404)
				dataStr=EntityUtils.toString(httpResponse.getEntity()).trim();
			
		} catch (Exception e) {
			dataStr = "";
		} 
		return convert(dataStr);
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
