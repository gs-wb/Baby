package com.yikang.health.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.cache.LogUtils;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskCompleteListener;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.utils.Utils;
import com.yikang.health.widget.dialog.CustomProgressLoad;

/**
 * 网络请求处理
 * 
 * @author hh
 * 
 */
public abstract class BasePostHttpTask extends AsyncTask<Object, Void, String> {
	static final String DEVICEINFO_KEY = "devInfo";
	private Context context;
	private CustomProgressLoad progressDialog;
	private TaskCompleteListener mTaskCompleteListener;

	protected int connectionId;
	protected String requestUrl;
	protected boolean isCanceled = false;
	protected boolean isDone = false;
	private int status_code = Constants.STATUS_OK;
	private String resultCode="";
	private static final int TIMEOUT_IN_MILLIONS = 5000;

	private LinkedBlockingQueue<String> resultQueue = new LinkedBlockingQueue<String>(
			1);

	public BasePostHttpTask(Context cxt, int connId, String url) {
		this.context = cxt;
		this.connectionId = connId;
		this.requestUrl = url;
	}

	/**
	 * 设置回调监听
	 * 
	 * @param taskCompleteListener
	 */
	public void setTaskCompleteListener(
			TaskCompleteListener taskCompleteListener) {
		this.mTaskCompleteListener = taskCompleteListener;
	}

	/**
	 * 是否弹出加载框
	 * 
	 * @return
	 */
	protected boolean isShowEProgressDialog() {
		return false;
	}

	/**
	 * 提示信息(如果弹出提示框)
	 * 
	 * @return
	 */
	protected String getWaitMessage() {
		return null;
	}

	/**
	 * 是否可取消任务
	 * 
	 * @return
	 */
	protected boolean isCancelAble() {
		return true;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (isShowEProgressDialog() && context != null) {
			if (context instanceof Activity) {
				Activity act = (Activity) context;
				if (act.isFinishing()) {
					LogUtils.e("Context is Activity and finished, do not use it to show dialog");
					return;
				}
			}
			if(progressDialog != null && progressDialog.isShowing())
				progressDialog.cancel();
			progressDialog = CustomProgressLoad.createDialog(context);
			progressDialog.setMessage(getWaitMessage());
			progressDialog.show();
		}
	}

	@Override
	protected String doInBackground(Object... params) {

		if (!Utils.isNetworkAvailable()) {
			status_code = Constants.STATUS_NETWORK_NOT_AVAILABLE;
			return null;
		}

		if (TextUtils.isEmpty(requestUrl)) {
			throw new IllegalArgumentException(
					"IllegalArgumentException: request url is empty");
		}

		HashMap<String, Object> hashMap = null;
		if (params == null|| params[0] == null || params.length == 0) {
			params[0] = new HashMap<String, Object>();
		}
		if (params[0] instanceof HashMap) {
			hashMap = (HashMap<String, Object>) params[0];

		}
		LogUtils.d("requesturl: " + requestUrl);
		LogUtils.d("params: " + hashMap);
		// 发送请求 ...
		String result;
		if(requestUrl.contains("http://apis.baidu.com")){
			result = requestByGet(requestUrl, hashMap);
		}else{
			result = requestByPost(requestUrl, hashMap);
		}

		return result;
	}

	/**
	 *
	 * @param url
	 * @return
	 */

	public String requestByGet(String url,Map<String, Object> heads) {
		HttpURLConnection conn = null;
		InputStream is = null;
		String  result = null;
		byte[] buf = new byte[128];
		ByteArrayOutputStream baoStream = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			if(heads!=null)for (Entry<String, Object> entry : heads.entrySet()) {// 拼接参数
				conn.setRequestProperty(entry.getKey(), entry.getValue().toString());
			}
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				baoStream = new ByteArrayOutputStream();
				int len = -1;
				while ((len = is.read(buf)) != -1) {
					baoStream.write(buf, 0, len);
				}
				result = baoStream.toString();
			}
		} catch (ConnectTimeoutException e) {
			conn.disconnect();
			LogUtils.printStackTrace("ConnectTimeoutException by request:"
					+ url, e);
			status_code = Constants.STATUS_NETWORK_TIMEOUT;
		} catch (SocketTimeoutException e) {
			conn.disconnect();
			LogUtils.printStackTrace("SocketTimeoutException by request:"
					+ url, e);
			status_code = Constants.STATUS_NETWORK_TIMEOUT;
		} catch (UnknownHostException e) {
			conn.disconnect();
			LogUtils.printStackTrace(
					"UnknownHostException by request:" + url, e);
			status_code = Constants.STATUS_SYSTEM_ERROR;
		} catch (IOException e) {
			conn.disconnect();
			LogUtils.printStackTrace("IOException by request: " + url, e);
			status_code = Constants.STATUS_NETWORK_ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.disconnect();
			LogUtils.printStackTrace("Exception by request:" + url, e);
			status_code = Constants.STATUS_SYSTEM_ERROR;
		} finally {
			try {
				if (baoStream != null)
					baoStream.close();
			} catch (IOException e) {
				LogUtils.printStackTrace("Exception while close the reader:"
						+ url, e);
			}
		}
		LogUtils.i(result);
		return result;
	}


	/**
	 *
	 * @param url
	 * @param params
	 * @return
	 */

	public String requestByPost(String url, Map<String, Object> params) {
		HttpURLConnection conn = null;
		OutputStream outStream = null;
		InputStreamReader reader = null;
		String result = ""; // 接收传回来的值
		try {
			StringBuffer data = new StringBuffer();// 创建一个StringBuffer来拼接参数
			for (Entry<String, Object> entry : params.entrySet()) {// 拼接参数
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue().toString()));
				data.append("&");
			}
			if (params.size() > 0) {
				data.deleteCharAt(data.length() - 1);
			}
			byte[] entity = data.toString().getBytes();// 得到实体数据

			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length",
					String.valueOf(entity.length));
			outStream = conn.getOutputStream();
			outStream.write(entity);
			if (conn.getResponseCode() == 200) {
				reader = new InputStreamReader(conn.getInputStream());
				result = new BufferedReader(reader).readLine();
			}
			resultQueue.offer(result);
			return createResult(convert(result));
		} catch (ConnectTimeoutException e) {
			conn.disconnect();
			LogUtils.printStackTrace("ConnectTimeoutException by request:"
					+ url, e);
			status_code = Constants.STATUS_NETWORK_TIMEOUT;
		} catch (SocketTimeoutException e) {
			conn.disconnect();
			LogUtils.printStackTrace("SocketTimeoutException by request:"
					+ url, e);
			status_code = Constants.STATUS_NETWORK_TIMEOUT;
		} catch (UnknownHostException e) {
			conn.disconnect();
			LogUtils.printStackTrace(
					"UnknownHostException by request:" + url, e);
			status_code = Constants.STATUS_SYSTEM_ERROR;
		} catch (IOException e) {
			conn.disconnect();
			LogUtils.printStackTrace("IOException by request: " + url, e);
			status_code = Constants.STATUS_NETWORK_ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.disconnect();
			LogUtils.printStackTrace("Exception by request:" + url, e);
			status_code = Constants.STATUS_SYSTEM_ERROR;
		} finally {
			try {
				if (outStream != null)
					outStream.close();
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				LogUtils.printStackTrace("Exception while close the reader:"
						+ url, e);
			}
		}
		LogUtils.i(result);
		return result;
	}


	/**
	 * 
	 * @param json
	 * @return
	 */
	
	protected String createResult(String json) {
		LogUtils.d("result: " + json);
		resultCode = handlerResult(context == null ? null : context,
				connectionId, json, mTaskCompleteListener);
		return json;
	}

	/**
	 * 
	 * @param context
	 * @param connCode
	 * @param result
	 * @param mTaskCompleteListener
	 * @return
	 */
	
	private String handlerResult(Context context, final int connCode,
			final String result,
			final TaskCompleteListener mTaskCompleteListener) {
		try {
			if(TextUtils.isEmpty(result))return "-1";
			JSONObject returnData = new JSONObject(result);
			final String resultCode = returnData.optString(
					Constants.FIELD_RESULTCODE, "-1");
			return resultCode;
		} catch (JSONException e) {
			LogUtils
					.printStackTrace(
							"An error occurred when call the method {TaskResultHandler->handlerResult()}",
							e);
		}
		return "-1";
	}


	@Override
	protected void onPostExecute(String result) {

		super.onPostExecute(result);

		if (!this.isCanceled) {
			if (isShowEProgressDialog()) {
				if (progressDialog != null && progressDialog.isShowing()) {
					try {
						progressDialog.cancel();
						progressDialog = null;
					} catch (Exception e) {
						LogUtils.e(LogUtils.getStackTraceString(e));
					}
				}
			}

			NetTaskManager.getTaskPool().removeTask(connectionId);
			switch (status_code) {
			case Constants.STATUS_OK:
				if (mTaskCompleteListener != null) {
					if(!TextUtils.isEmpty(result.toString())){
						mTaskCompleteListener.onTaskCompleted(resultCode, result,
								connectionId);
					}else{
						((TaskExpandListener) mTaskCompleteListener)
						.onTaskError(resultCode, connectionId,
								ServerConnect.getResultInfo(Constants.STATUS_SYSTEM_ERROR));
					}
				}
				break;
			default:
				if (mTaskCompleteListener != null
						&& mTaskCompleteListener instanceof TaskExpandListener) {
					((TaskExpandListener) mTaskCompleteListener)
							.onTaskError(resultCode, connectionId,
									ServerConnect.getResultInfo(status_code));
				} else {
					Toast.makeText(YIKApplication.getContext(),
							ServerConnect.getResultInfo(status_code),
							Toast.LENGTH_LONG).show();
				}
			}

		} else {
			onTaskCancel();
		}

		isDone = true;
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
	/**
	 * 处理参数
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	protected List<NameValuePair> buildParams(Map<String, String> params)
			throws IOException {

		if (params == null || params.isEmpty()) {
			return null;
		}

		int size = params.size();

		List<NameValuePair> ps = new ArrayList<NameValuePair>(size);
		Set<Entry<String, String>> entries = params.entrySet();

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();

			if (areNotEmpty(name, value)) {
				ps.add(new BasicNameValuePair(name, value));
			}
		}

		return ps;
	}

	private boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			return false;
		} else {
			for (String value : values) {
				result &= !TextUtils.isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 中断任务
	 */
	protected void cancelTask(boolean doCallback) {
		this.isCanceled = true;
		if (!doCallback) {
			this.cancel(true);
			if (progressDialog != null && progressDialog.isShowing()) {
				try {
					progressDialog.cancel();
					progressDialog = null;
				} catch (Exception e) {
					LogUtils.e(LogUtils.getStackTraceString(e));
				}
			}
			onTaskCancel();
		}
		LogUtils.d("the request task is cancelld:" + requestUrl);
	}

	protected void onTaskCancel() {
		if (mTaskCompleteListener != null
				&& mTaskCompleteListener instanceof TaskExpandListener) {
			((TaskExpandListener) mTaskCompleteListener)
					.onTaskCanceled();
		}
	}

	protected String nextResult() {
		return resultQueue.poll();
	}

}
