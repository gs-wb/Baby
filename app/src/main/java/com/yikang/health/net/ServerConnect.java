package com.yikang.health.net;

import android.content.Context;
import android.text.TextUtils;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskCompleteListener;

/**
 * 网络请求类
 * @author zwb
 *
 */
public class ServerConnect {
	// 开发模式
	private final static int MODEL_DEV1 = 0;
	// 测试模式
	private final static int MODEL_PRD = 1;
	public static int mode = MODEL_DEV1;
	private String host;
	private static ServerConnect instance;

	public static ServerConnect getInstance() {
		synchronized (ServerConnect.class) {
			if (instance == null) {
				instance = new ServerConnect();
			}
		}
		return instance;
	}

	private ServerConnect() {
		String configMode = YIKApplication.getContext().configMode;
		if (!TextUtils.isEmpty(configMode)) {
			if (configMode.equals(String.valueOf(MODEL_DEV1))) {
				mode = MODEL_DEV1;
			} else if (configMode.equals(String.valueOf(MODEL_PRD))) {
				mode = MODEL_PRD;
			}
			initNetUrl();
		}
	}

	private void initNetUrl() {
		// TODO Auto-generated method stub
		switch (mode) {
		case MODEL_DEV1:
			host = "http://120.76.136.195:20165/";
			break;
		case MODEL_PRD:
			host = "http://120.76.136.195:20165/";
			break;
		default:
			break;
		}
	}

	/**
	 * 提交一次请求
	 * 
	 * @param listener
	 *            回调接口
	 * @param connCode
	 *            api请求代码(具体参照HttpConstants中的定义)
	 * @param params
	 *            (参数map)
	 */
	public void requestToServer(Context context,
			TaskCompleteListener listener, int connCode,
			Object... params) {

		NetTaskManager.getTaskPool().addNewTaskOnUIThread(context, connCode,
				getRequestUrl(connCode), listener, params);
	}
	
	/**
	 * 
	 * @param context
	 * @param listener
	 * @param connCode
	 */
	
	public void requestToServer(Context context,
			TaskCompleteListener listener, int connCode,String endUrl,Object... params) {

		NetTaskManager.getTaskPool().addNewTaskOnUIThread(context, connCode,
				getRequestUrl(connCode), listener, params);
	}

	/**
	 * 提交一次请求(弹窗)
	 * 
	 *            请求的Activity
	 * @param listener
	 *            回调接口
	 * @param connCode
	 *            api请求代码(具体参照HttpConstants中的定义)
	 * @param params
	 *            (参数map)
	 * @param dialogMsg
	 *            弹窗要显示的提示信息
	 */
	public void requestToServer(Context context,
			TaskCompleteListener listener, int connCode, Object params,
			String dialogMsg) {
		NetTaskManager.getTaskPool().addNewTask(context, connCode,
				getRequestUrl(connCode), listener, dialogMsg, params);
	}

	public void requestToServer(Context context,
								TaskCompleteListener listener,String url, int connCode, Object params,
								String dialogMsg) {
		NetTaskManager.getTaskPool().addNewTask(context, connCode,
				url, listener, dialogMsg, params);
	}

	private String getRequestUrl(int connCode) {
		// TODO Auto-generated method stub
		return host + getOperationType(connCode);
	}
	private String getOperationType(int connId) {
		switch (connId) {
		case Constants.CHECK_VERSION:
			return "system/checkAndroidVersion";
		case Constants.AD_BANNER_LIST:
			return "system/bannerList";
		case Constants.GET_USER:
			return "user/getUser";
		case Constants.ADD_USER:
			return "user/addUser";
		case Constants.ADD_CODE:
			return "user/addCode";
		case Constants.GET_USER_LOGIN:
			return "user/getUserLogin";
		case Constants.THIRD_LOGIN:
			return "user/thirdLogin";
		case Constants.THIRD_REGISTER:
			return "user/thirdRegister";
		case Constants.GET_USER_EXPAND:
			return "user/getUserExpand";
		case Constants.ADD_USER_EXPAND:
			return "user/addUserExpand";
		case Constants.EDIT_USER_EXPAND:
			return "user/editUserExpand";
		case Constants.GET_AREAHOT_LIST:
			return "user/getAreaHotList";
		case Constants.GET_AREA_LIST:
			return "user/getAreaList";
		case Constants.ADD_FEED_BACK:
			return "user/addFeedback";
		case Constants.EDIT_USER_PHONE:
			return "user/editUserPhone";
		case Constants.GET_NOTICE_LIST:
			return "user/getNoticeList";
		case Constants.GET_NOTICE_DETAIL:
			return "user/getNoticeDetail";
		case Constants.FIND_PWD:
			return "user/findPwd";
		case Constants.VALIDATE_CODE:
			return "user/validateCode";
		case Constants.RESET_PWD:
			return "user/resetPwd";
		case Constants.GET_CONTENT_LIST:
			return "content/getContentList";
		case Constants.GET_MP3_LIST:
			return "content/getMp3List";
		case Constants.GET_VIDEO_LIST:
			return "content/getVideoList";
		default:
			return "";
		}
	}

	/**
	 * 获取反馈文本信息
	 * 
	 * @param resultCode
	 * @return
	 */
	public static String getResultInfo(int resultCode) {
		switch (resultCode) {
		case Constants.STATUS_NETWORK_NOT_AVAILABLE:
			return YIKApplication.getContext().getString(
					R.string.str_status_network_not_available);
		case Constants.STATUS_NETWORK_TIMEOUT:
			return YIKApplication.getContext().getString(
					R.string.str_status_network_timeout);
		case Constants.STATUS_SYSTEM_ERROR:
			return YIKApplication.getContext().getString(
					R.string.str_status_system_error);
		case Constants.STATUS_NETWORK_ERROR:
			return YIKApplication.getContext().getString(
					R.string.str_status_network_error);
		default:
			return "";
		}
	}
}
