package com.yikang.health.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yikang.health.YIKApplication;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.widget.SuperscriptSpanAdjuster;

/**
 * Description: 工具类
 * 
 * @version $Revision: 1.0 $
 * @author zwb
 * @email
 * @date:
 * @time: 
 */
public class Utils {
	public static final int NETWORK_TYPE_NONE = 0; // 无网络
	public static final int NETWORK_TYPE_WIFI = 1; // Wifi
	public static final int NETWORK_TYPE_MOBILE = 2; // 数据网络
	public static final int NETWORK_TYPE_MOBILE_4G = 3; // gprs 4gʽ
	public static final int NETWORK_TYPE_MOBILE_3G = 4; // gprs 3g
	public static final int NETWORK_TYPE_MOBILE_2G = 5; // gprs 2g
	private static Typeface numTypeface;

	/**
	 * 检查网络
	 * 
	 * @return
	 */
	public static boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) YIKApplication
				.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		boolean isAvailable = activeNetworkInfo != null
				&& activeNetworkInfo.isConnected();
		return isAvailable;
	}

	

	// 判断有无网络
	public static boolean netWork() {
		Context context = YIKApplication.getContext();
		ConnectivityManager mConnectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 检查网络连接，如果无网络可用，就不需要进行连网操作等
		NetworkInfo info = mConnectivity.getActiveNetworkInfo();
		if (info == null || !mConnectivity.getBackgroundDataSetting()) {
			return false;
		}
		// 判断网络连接类型，只有在3G或wifi里进行一些数据更新。
		int netType = info.getType();
		if (netType == ConnectivityManager.TYPE_WIFI) {
			return info.isConnected();
		} else if (netType == ConnectivityManager.TYPE_MOBILE) {
			return info.isConnected();
		} else {
			return false;
		}
	}

	/**
	 * 判断当前连接网络的具体类型
	 * 
	 * @param mContext
	 * @return
	 */

	public static int getNetworkTypeWifiMobile(Context mContext) {
		if (mContext == null) {
			return NETWORK_TYPE_NONE;
		}
		Context context = mContext;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return NETWORK_TYPE_NONE;
		} else {
			NetworkInfo wifi = connectivity
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI); // wifi
			if (wifi != null && wifi.getState() == State.CONNECTED) {
				return NETWORK_TYPE_WIFI;
			}

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				try {
					// if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE
					// ||
					// info.getSubtype() ==TelephonyManager.NETWORK_TYPE_HSPAP){
					// return NETWORK_TYPE_MOBILE_4G;
					// }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (info.getSubtype() == TelephonyManager.NETWORK_TYPE_UMTS
						|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_0
						|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_A
						|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_HSDPA
						|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_B
						|| info.getSubtype() == 17) { // td_scdma
					return NETWORK_TYPE_MOBILE_3G;
				}

				if (info.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS
						|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
						|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA) {
					return NETWORK_TYPE_MOBILE_2G;
				}
			}
			return NETWORK_TYPE_NONE;
		}
	}

	
	public static int getByte(String str) {
		byte[] b = null;
		try {
			b = str.getBytes("gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b.length;
	}

	/**
	 * 
	 * @param str
	 *            要修改的字符串
	 * @param absoluteSizeSpan1
	 *            修改特定位置字符串的字号
	 * @param foregroundColorSpan1
	 *            修改特定位置字符串的颜色
	 * @param i
	 *            要修改的字符串的起始位置
	 * @param j
	 *            要修改的字符串的结束位置
	 * @return
	 */

	public static CharSequence setTextViewContentStyle(String str,
			AbsoluteSizeSpan absoluteSizeSpan1,
			ForegroundColorSpan foregroundColorSpan1, int i, int j) {
		// TODO Auto-generated method stub
		SpannableString spStr = new SpannableString(str);
		if (foregroundColorSpan1 != null) {
			spStr.setSpan(foregroundColorSpan1, i, j,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if (absoluteSizeSpan1 != null) {
			spStr.setSpan(absoluteSizeSpan1, i, j,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return spStr;
	}

	/**
	 * 
	 * @param str
	 *            注释同上
	 * @param absoluteSizeSpan1
	 * @param absoluteSizeSpan2
	 * @param foregroundColorSpan1
	 * @param i
	 * @param j
	 * @param foregroundColorSpan2
	 * @param k
	 * @param l
	 * @return
	 */

	public static SpannableString setTextViewContentStyle(String str,
			AbsoluteSizeSpan absoluteSizeSpan1,
			AbsoluteSizeSpan absoluteSizeSpan2,
			ForegroundColorSpan foregroundColorSpan1, int i, int j,
			ForegroundColorSpan foregroundColorSpan2, int k, int l) {
		// TODO Auto-generated method stub
		SpannableString spStr = new SpannableString(str);
		if (foregroundColorSpan1 != null) {
			spStr.setSpan(foregroundColorSpan1, i, j,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if (absoluteSizeSpan1 != null) {
			spStr.setSpan(absoluteSizeSpan1, i, j,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		if (foregroundColorSpan2 != null) {
			spStr.setSpan(foregroundColorSpan2, k, l,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if (absoluteSizeSpan2 != null) {
			spStr.setSpan(absoluteSizeSpan2, k, l,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return spStr;
	}

	/** 获得屏幕物理英寸 */
	public static double getScreenInches(BaseActivity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		BigDecimal screenInches = new BigDecimal(Math.sqrt(x + y));
		double scaleCcreenInches = screenInches.setScale(1,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return scaleCcreenInches;
	}

	/** 根据屏幕分辨率 dp转成px */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/** 根据屏幕分辨率 px转成dp */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	public static int getWidthPixels(Activity context){
		WindowManager m = context.getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		DisplayMetrics outMetrics = new DisplayMetrics();
		d.getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 *  获得自定义的字体库
	 * @return
	 */
	public static Typeface getNumTypeface() {
		if (numTypeface == null)
			numTypeface = Typeface.createFromAsset(YIKApplication
					.getContext().getAssets(), "font/modern_home_numbers.ttf");
		return numTypeface;
	}


	/**
	 * 检查SDK版本是否为3.0以上
	 * 
	 * @return
	 */
	public static boolean hasHoneycomb() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}

	/**
	 * 检查SDK版本是否为4.1以上
	 * 
	 * @return
	 */
	public static boolean hasJellyBean() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
	}

	/**
	 * 检查SDK版本是否为4.4以上
	 * 
	 * @return
	 */
	public static boolean hasKitKat() {
		return Build.VERSION.SDK_INT >= 19;
	}

	/**
	 * 检查验证码和手机号码的合法性
	 * 
	 * @return
	 */
	public static boolean checkSMSvaildateAndPhone(String phoneNum) {
		// TODO 正则校验手机号 ""
		if (phoneNum != null) {
			if (!phoneNum
					.matches("(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(19[0-9]{1}))+[0-9]{8})")) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 获取短息校验码正则
	 */

	public static String SMSCODE_REGEX = "(?<![0-9])([0-9]{6})(?![.0-9])";

	/**
	 * 验证码校验
	 * 
	 * @param smscode
	 */
	public static boolean checkSMSCode(String smscode) {
		if (smscode.length() != 6) {
			return false;
		}
		if (!isNumeric(smscode)) {
			return false;
		}
		return true;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证银行卡卡号
	 * 
	 * @return
	 */

	public static boolean checkBankCard(EditText bankcardnumber) {
		String bankNum = bankcardnumber.getText().toString().replace(" ", "");
		if (bankNum.length() > 23 || bankNum.length() < 16) {
			return false;
		}
		return bankNum.matches("^[0-9]{15,19}$");
	}


	/**
	 * 弹出软键盘
	 * 
	 * @param editText
	 */

	public static void openInputMethod(final EditText editText) {

		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {
				InputMethodManager inputManager = (InputMethodManager) editText
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(editText, 0);
			}
		}, 700);
	}


	/**
	 * 设置输入框的焦点
	 * 
	 * @param editText
	 */

	public static void editTextFocus(EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}

	/**
	 * 标题栏底部横线滑动动画
	 * 
	 * @param index
	 * @param indexEnd
	 * @param ivBottomLine
	 */

	public static void initStartAnimation(int index, int indexEnd,
			View ivBottomLine) {
		TranslateAnimation animation = new TranslateAnimation(index, indexEnd,
				0f, 0f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(100);
		animation.setFillAfter(true);
		ivBottomLine.startAnimation(animation);
	}

	/**
	 * 获得当前程序版本信息
	 * 
	 * @return
	 * @throws Exception
	 */

	public static PackageInfo getVersionName() {
		PackageInfo packInfo = null;
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = YIKApplication.getContext()
					.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			packInfo = packageManager.getPackageInfo(YIKApplication
					.getContext().getPackageName(), 0);
			return packInfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packInfo;
	}

	/**
	 * 用来判断服务是否运行.
	 * 
	 * @param context
	 * @param className
	 *            判断的服务名字
	 * @return true 在运行 false 不在运行
	 */
	public static boolean isServiceRunning(String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) YIKApplication.getContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
	
	
	
	public static void setViewTouchListener(View... v){
		for (int i = 0; i < v.length; i++) {
			if(v[i]!=null)
				v[i].setOnTouchListener(BtnTouchListener.TouchDark);
		}
	}

	public static String[] bank = { "请选择提款银行", "工商银行", "招商银行", "农业银行", "建设银行",
			"邮政储蓄", "中信银行", "兴业银行", "光大银行", "华夏银行", "深圳发展银行", "广发银行", "中国民生银行",
			"上海浦东发展银行", "农村信用合作社", "农村商业银行", "农村合作银行", "城市商业银行", "城市信用合作社",
			"平安银行", "上海银行", "北京银行", "恒丰银行", "渤海银行", "广州银行", "珠海南通银行", "天津银行",
			"浙商银行", "浙江商业银行", "宁波国际银行", "宁波银行", "温州银行", "南京银行", "常熟农村商业银行",
			"福建亚洲银行", "福建兴业银行", "徽商银行", "厦门国际银行", "青岛市商业银行", "济南市商业银行", "重庆银行",
			"成都市商业银行", "哈尔滨银行", "包头市商业银行", "南昌市商业银行", "贵阳商业银行", "兰州市商业银行", };
	
	public static int getBankValue(String bangKey){
		Map<String, Object> bankMap = new HashMap<String, Object>();
		bankMap.put("工商银行", 2);
		bankMap.put("招商银行", 1);
		bankMap.put("农业银行", 13);
		bankMap.put("建设银行", 3);
		bankMap.put("邮政储蓄", 25);
		bankMap.put("中信银行", 8);
		bankMap.put("兴业银行", 9);
		bankMap.put("光大银行", 10);
		bankMap.put("华夏银行", 11);
		bankMap.put("深圳发展银行", 1001);
		bankMap.put("广发银行", 1000);
		bankMap.put("中国民生银行", 12);
		bankMap.put("上海浦东发展银行", 4000);
		bankMap.put("农村信用合作社", 15);
		bankMap.put("农村商业银行", 16);
		bankMap.put("农村合作银行", 17);
		bankMap.put("城市商业银行", 18);
		bankMap.put("城市信用合作社", 19);
		bankMap.put("平安银行", 23);
		bankMap.put("上海银行", 4001);
		bankMap.put("北京银行", 2000);
		bankMap.put("恒丰银行", 22);
		bankMap.put("渤海银行", 24);
		bankMap.put("广州银行", 1001);
		bankMap.put("珠海南通银行", 1003);
		bankMap.put("天津银行", 3000);
		bankMap.put("浙商银行", 5000);
		bankMap.put("浙江商业银行", 5001);
		bankMap.put("宁波国际银行", 5002);
		bankMap.put("宁波银行", 5003);
		bankMap.put("温州银行", 5004);
		bankMap.put("南京银行", 6000);
		bankMap.put("常熟农村商业银行", 6001);
		bankMap.put("福建亚洲银行", 7000);
		bankMap.put("福建兴业银行", 7001);
		bankMap.put("徽商银行", 7002);
		bankMap.put("厦门国际银行", 7003);
		bankMap.put("青岛市商业银行", 8000);
		bankMap.put("济南市商业银行", 8001);
		bankMap.put("重庆银行", 9000);
		bankMap.put("成都市商业银行", 10000);
		bankMap.put("哈尔滨银行", 11000);
		bankMap.put("包头市商业银行", 12000);
		bankMap.put("南昌市商业银行", 13000);
		bankMap.put("贵阳商业银行", 14000);
		bankMap.put("兰州市商业银行", 15000);
		return Integer.parseInt(bankMap.get(bangKey).toString());
		
	}
	
	public static String[] shengfen = { "请选择省份", "北京", "上海", "重庆", "天津", "广东",
			"河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西",
			"山东", "河南", "湖北", "湖南", "广西", "海南", "四川", "贵州", "云南", "西藏", "陕西",
			"甘肃", "青海", "宁夏", "新疆" };

	public static String[][] chengshi = {
			{ "请选择城市" },
			{ "北京" },
			{ "上海" },
			{ "重庆" },
			{ "天津" },
			{ "广州", "深圳", "韶关", "珠海", "汕头", "佛山", "江门", "湛江", "茂名", "肇庆", "惠州",
					"梅州", "汕尾", "河源", "阳江", "清远", "东莞", "中山", "潮州", "揭阳", "云浮" },
			{ "石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊",
					"衡水" },
			{ "太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁",
					"永济" },
			{ "呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖尔", "乌兰察布",
					"兴安", "锡林郭勒", "阿拉善", "临河", "东胜", "集宁", "锡林浩特", "海拉尔",
					"乌兰浩特" },
			{ "沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦",
					"铁岭", "朝阳", "葫芦岛" },
			{ "长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边" },
			{ "哈尔滨", "齐齐哈尔", "鸡西", "鹤岗", "双鸭山", "大庆", "伊春", "佳木斯", "七台河",
					"牡丹江", "黑河", "绥化", "大兴安岭" },
			{ "南京", "无锡", "徐州", "常州", "苏州", "南通", "连云港", "淮安", "盐城", "扬州",
					"镇江", "泰州", "宿迁", "淮阴", "张家港" },
			{ "杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水",
					"温岭" },
			{ "合肥", "芜湖", "蚌埠", "淮南", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州",
					"阜阳", "宿州", "巢湖", "六安", "亳州", "池州", "宣城" },
			{ "福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德" },
			{ "南昌", "景德镇", "萍乡", "九江", "新余", "鹰潭", "赣州", "吉安", "宜春", "抚州", "上饶" },
			{ "济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照",
					"莱芜", "临沂", "德州", "聊城", "滨州", "菏泽" },
			{ "郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作", "濮阳", "许昌",
					"漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店", "济源" },
			{ "武汉", "黄石", "十堰", "宜昌", "襄樊", "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁",
					"随州", "恩施", "仙桃", "潜江", "天门", "神农架" },
			{ "长沙", "株洲", "湘潭", "衡阳", "邵阳", "岳阳", "常德", "张家界", "益阳", "郴州",
					"永州", "怀化", "娄底", "湘西" },
			{ "南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色",
					"贺州", "河池", "来宾", "崇左", "桂平" },
			{ "海口", "三亚", "五指山", "琼海", "儋州", "文昌", "万宁", "东方", "琼山", "临高",
					"陵水", "澄迈", "定安", "屯昌", "昌江", "白沙", "琼中", "乐东", "保亭", "陵水" },
			{ "成都", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁", "内江", "乐山",
					"南充", "眉山", "宜宾", "广安", "达州", "雅安", "巴中", "资阳", "阿坝", "甘孜",
					"凉山", "达川", "阆中" },
			{ "贵阳", "六盘水", "遵义", "安顺", "铜仁", "黔西南", "毕节", "黔东南", "黔南" },
			{ "昆明", "曲靖", "玉溪", "保山", "昭通", "丽江", "思茅", "临沧", "楚雄", "红河州",
					"文山", "西双版纳", "大理", "德宏", "怒江傈", "迪庆", "东川", "怒江" },
			{ "拉萨", "昌都", "山南", "日喀则", "那曲", "阿里", "林芝" },
			{ "西安", "铜川", "宝鸡", "咸阳", "渭南", "延安", "汉中", "榆林", "安康", "商洛" },
			{ "兰州", "嘉峪关", "金昌", "白银", "天水", "武威", "张掖", "平凉", "酒泉", "庆阳",
					"定西", "陇南", "临夏", "甘南" },
			{ "西宁", "海东", "海北", "黄南", "海南", "果洛", "玉树", "海西" },
			{ "银川", "石嘴山", "吴忠", "固原", "中卫", "银南" },
			{ "乌鲁木齐", "克拉玛依", "吐鲁番", "哈密", "昌吉", "博尔塔拉", "巴音郭楞", "阿克苏", "克孜勒苏",
					"喀什", "和田", "伊犁", "塔城", "阿勒泰", "石河子", "阿拉尔", "图木舒克", "五家渠" } };
}
