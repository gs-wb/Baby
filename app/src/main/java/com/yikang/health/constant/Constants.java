package com.yikang.health.constant;

import android.os.Environment;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;

public class Constants {
	
	public static final int STATUS_OK = 10001;
	public static final int STATUS_NETWORK_NOT_AVAILABLE = 10002; // 当前网络不可用
	public static final int STATUS_NETWORK_TIMEOUT = 10003; // 网络连接超时
	public static final int STATUS_SYSTEM_ERROR = 10004; // 系统访问异常
	public static final int STATUS_NETWORK_ERROR = 10005; // 网络连接异常
	public static final int STATUS_MOVED_TEMPORARILY = 10006; // 重定向
	public static final int STATUS_PARAMETERS_ERROR = 10007; // 参数错误

	public static final String FIELD_RESULTCODE = "error";
	public static final String FIELD_RESULTCODE2 = "errNum";
	public static final String FIELD_RESULTMSG = "message";
//	public static final String FIELD_SESSIONID = "sessionId";
	public static final String RESULT_SUCCESS = "0";
	
	public static final int CONNECTTIMEOUT = 30 * 1000;
	
	public final static String myFilePath = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/storeCacheTemp";          // 文件保存路径

	public static final String FROM_APP = YIKApplication.getContext()
			.getResources().getString(R.string.channel_id);
	
	public static String APP_DOWNLOAD_URL;
	//服务器上APP最新版版本号
	public static int APP_VERSON_CODE = 0;
	//更新内容说明
	public static String APP_UPDATE_CONTENT;
	
	/*--------系统模块接口 常量1开始--------*/
	// 验证安卓版本 system/checkAndroidVersion
	public static final int CHECK_VERSION = 11;
	// 广告轮播图列表 system/bannerList
	public static final int AD_BANNER_LIST = 12;
	
	/*--------用户模块接口  常量2开始--------*/
	// 查询用户信息  user/getUser
	public static final int GET_USER = 21;
	// 注册新用户 user/addUser
	public static final int ADD_USER = 22;
	// 获取校验码 user/addCode
	public static final int ADD_CODE = 23;
	// 用户登录 user/getUserLogin
	public static final int GET_USER_LOGIN = 24;
	// 第三方帐号登录 user/thirdLogin
	public static final int THIRD_LOGIN = 25;
	// 第三方帐号绑定注册 user/thirdRegister
	public static final int THIRD_REGISTER = 26;
	// 查询用户个人扩展信息  user/getUserExpand
	public static final int GET_USER_EXPAND = 27;
	// 新建用户个人扩展信息  user/addUserExpand
	public static final int ADD_USER_EXPAND = 28;
	// 编辑用户个人扩展信息 user/editUserExpand
	public static final int EDIT_USER_EXPAND = 29;
	// 获得热门城市 user/getAreaHotList
	public static final int GET_AREAHOT_LIST = 210;
	// 获得所有城市  user/getAreaList
	public static final int GET_AREA_LIST = 212;
	// 添加意见反馈信息  user/addFeedback
	public static final int ADD_FEED_BACK = 213;
	// 修改用户手机号码 user/editUserPhone
	public static final int EDIT_USER_PHONE = 214;
	// 首页行业资讯列表 user/getNoticeList
	public static final int GET_NOTICE_LIST = 215;
	// 首页行业资讯列表  user/getNoticeDetail
	public static final int GET_NOTICE_DETAIL = 216;
	// 重发验证码  user/findPwd
	public static final int FIND_PWD = 217;
	// 验证校验码  校验码5分钟后过期  user/validateCode
	public static final int VALIDATE_CODE = 218;
	// 重置密码   user/resetPwd
	public static final int RESET_PWD = 219;

	/*--------内容模块接口  常量3开始--------*/
	// 查询文章信息  content/getContentList
	public static final int GET_CONTENT_LIST = 31;
	// 查询音频信息  content/getMp3List
	public static final int GET_MP3_LIST = 32;
	// 查询视频信息 content/getVideoList
	public static final int GET_VIDEO_LIST = 33;

	/*--------百度公共接口  常量9开始--------*/
	//获取天气的接口
	public static final int GET_WEATHER_DATA = 91;

	//获取天气的接口
	public static final int GET_BABYLORE_DATA = 92;

	//  http://apistore.baidu.com/apiworks/servicedetail/112.html
	// 百度公共接口
	public static final String  COMMON_API = "http://apis.baidu.com/apistore/";
	// apikey
	public static final String API_KEY_VALUE = "apikey";
	// 百度公共接口 key
	public static final String  API_KEY = "9cb6019d0dbf074445e4b4f21f989784";
	// 百度公共接口 城市名称
	public static final String  CITY_NAME = "cityname";
	public class PlayerMsg {
		public static final int PLAY_MSG = 1;		//播放
		public static final int PAUSE_MSG = 2;		//暂停
		public static final int STOP_MSG = 3;		//停止
		public static final int CONTINUE_MSG = 4;	//继续
		public static final int PRIVIOUS_MSG = 5;	//上一首
		public static final int NEXT_MSG = 6;		//下一首
		public static final int PROGRESS_CHANGE = 7;//进度改变
		public static final int PLAYING_MSG = 8;	//正在播放

		public static final String UPDATE_ACTION = "com.wwj.action.UPDATE_ACTION"; // 更新动作
		public static final String CTL_ACTION = "com.wwj.action.CTL_ACTION"; // 控制动作
		public static final String MUSIC_CURRENT = "com.wwj.action.MUSIC_CURRENT"; // 音乐当前时间改变动作
		public static final String MUSIC_DURATION = "com.wwj.action.MUSIC_DURATION";// 音乐播放长度改变动作
		public static final String MUSIC_PLAYING = "com.wwj.action.MUSIC_PLAYING"; // 音乐正在播放动作
		public static final String REPEAT_ACTION = "com.wwj.action.REPEAT_ACTION"; // 音乐重复播放动作
		public static final String SHUFFLE_ACTION = "com.wwj.action.SHUFFLE_ACTION";// 音乐随机播放动作
		public static final String SHOW_LRC = "com.wwj.action.SHOW_LRC"; // 通知显示歌词
	}
}
