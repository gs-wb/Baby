package com.yikang.health.net;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

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


	public static String SendRequest(String adress_Http, String strJson) {

		String returnLine = "";
		try {

			System.out.println("**************开始http通讯**************");
			System.out.println("**************调用的接口地址为**************" + adress_Http);
			System.out.println("**************请求发送的数据为**************" + strJson);
			URL my_url = new URL(adress_Http);
			HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(true);

			connection.setRequestProperty("Content-Type", "application/json");

			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());

			byte[] content = strJson.getBytes("utf-8");

			out.write(content, 0, content.length);
			out.flush();
			out.close(); // flush and close

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

			//StringBuilder builder = new StringBuilder();

			String line = "";

			System.out.println("Contents of post request start");

			while ((line = reader.readLine()) != null) {
				// line = new String(line.getBytes(), "utf-8");
				returnLine += line;

				System.out.println(line);

			}

			System.out.println("Contents of post request ends");

			reader.close();
			connection.disconnect();
			System.out.println("========返回的结果的为========" + convert(returnLine));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return convert(returnLine);

	}
}
