/*
 *ExpanceConstants.java
 *Classes：com.huaxia.lotteryitem.ui.constant.ExpanceConstants
 *wangxiaojun Create at 2014-11-27 下午2:12:38	
 */
package com.yikang.health.constant;

/**
 * @author hh
 */
public class ExceptionConstants {
	// 发信息给handler
	public static final int LOTTERY_REQUEST_SUCCESS = 3;
	// 返回
	public static final int LOTTERY_REQUEST_ERROR = 6;
	// 网络错误
	public static final int NETWORK_ERROR = 8;
	// 是否连网 -1代表原始状态要重新判断 1代表有网络 0代表无网络
	public static final int NETWORK_STOP = 0;
	//超时
	public static final int NETWORK_TIMEOUT = 10;
	// 发送普通数据
	public static final int LOTTERY_SEND_DATA = 3;
	// 发送异常数据
	public static final int LOTTERY_SEND_ABNORMAL = 4;
}
