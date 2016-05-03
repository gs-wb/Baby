package com.yikang.health;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.baidu.mapapi.SDKInitializer;
import com.yikang.health.cache.DataCache;
import com.yikang.health.cache.LogUtils;
import com.yikang.health.net.PublicHttpData;
import com.yikang.health.utils.SharePreferenceUtil;
import com.yikang.health.utils.SharedPreferencesUtils;

/**
 * Application
 * 
 * @author hh
 * 
 */

public class YIKApplication extends Application {
	public String configMode;
	public static YIKApplication instance;
	public LayoutInflater inflater;
	public static SharedPreferencesUtils utils;
	public static PublicHttpData client = PublicHttpData.getInstance();
	
	/**
	 * 基本表情 总共有多少页
	 */
	public final int BASICFACE_NUM_PAGE = 5;

	/**
	 * 基本表情 每页20个表情,还有最后一个删除button
	 */
	public int BASICFACE_NUM = 20;
	private SharePreferenceUtil mSpUtil;
	
	public YIKApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public final static String USER_SHAREDPREFERENCES = "circle";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		SDKInitializer.initialize(YIKApplication.getContext());
		inflater = LayoutInflater.from(this);
		utils = new SharedPreferencesUtils(this);
		configMode = loadProperties("config.properties").getProperty("mode");
		mSpUtil = new SharePreferenceUtil(this, USER_SHAREDPREFERENCES);
		/**
		 * 初始化表情Map
		 */
		initFaceMap();
		
		// ImageLoaderConfiguration config = new
		// ImageLoaderConfiguration.Builder(getApplicationContext())
		// .discCacheSize(50 * 1024 * 1024).discCacheFileCount(100)
		// .memoryCache(new LruMemoryCache(5 * 1024 * 1024)).memoryCacheSize(5 *
		// 1024 * 1024)
		// .discCacheSize(10 * 1024 * 1024).build();
		// ImageLoader.getInstance().init(config);
	}

	public static YIKApplication getContext() {
		return instance;
	}

	private Properties loadProperties(String filename) {
		Properties props = new Properties();
		try {
			// InputStream in = Thread.currentThread().getContextClassLoader()
			// .getResourceAsStream(filename);
			props.load(YIKApplication.getContext().getAssets().open(filename));
		} catch (FileNotFoundException e) {
			LogUtils.e("Could not find the properties file.", e);
		} catch (IOException e) {
			LogUtils.e("Could not open the properties file.", e);
		}
		return props;
	}

	public void exit0() {
		final Timer exitTimer = new Timer();
		exitTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// Intent intent = new Intent();
				// intent.setClassName(getPackageName(),
				// QpNewsMainActivity.class.getName());
				// PendingIntent restartIntent =
				// PendingIntent.getActivity(YIKApplication.getContext(), 0,
				// intent, Intent.FLAG_ACTIVITY_NEW_TASK);
				// AlarmManager mgr = (AlarmManager)
				// YIKApplication.getContext().getSystemService(Context.ALARM_SERVICE);
				// mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 500,
				// restartIntent);
				// if(!isServiceRunning("com.yikang.health.service.UpdateAppService")){
				// android.os.Process.killProcess(android.os.Process.myPid());
				// }
				return;
			}
		}, 500);
		ArrayList<Activity> list = DataCache.getDataCache()
				.getActivityNowList();
		for (int i = 0; i < list.size(); i++) {
			((Activity) list.get(i)).finish();
		}
	}

	/**
	 * 
	 * @param className
	 * @return
	 */

	public boolean isServiceRunning(String className) {
		ActivityManager activityManager = (ActivityManager) this
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (serviceList != null && !serviceList.isEmpty()) {
			for (int i = 0; i < serviceList.size(); i++) {
				if (serviceList.get(i).service.getClassName().equals(className)) {
					return true;
				}
			}
		}
		return false;
	}
	public synchronized SharePreferenceUtil getSpUtil() {
		if (mSpUtil == null)
			mSpUtil = new SharePreferenceUtil(this,
					USER_SHAREDPREFERENCES);
		return mSpUtil;
	}
	/**
	 * 基本表情 Map
	 */
	private Map<String, Integer> mFaceMap = new LinkedHashMap<String, Integer>();
	private void initFaceMap() {
		mFaceMap.put("[14]", R.drawable.f000);//
		mFaceMap.put("[13]", R.drawable.f001);//
		mFaceMap.put("[28]", R.drawable.f002);//
		mFaceMap.put("[21]", R.drawable.f003);//
		mFaceMap.put("[40]", R.drawable.f004);//
		mFaceMap.put("[39]", R.drawable.f005);//
		mFaceMap.put("[41]", R.drawable.f006);//
		mFaceMap.put("[63]", R.drawable.f007);//
		mFaceMap.put("[64]", R.drawable.f008);//
		mFaceMap.put("[06]", R.drawable.f009);//
		mFaceMap.put("[10]", R.drawable.f010);//
		mFaceMap.put("[34]", R.drawable.f011);//
		mFaceMap.put("[17]", R.drawable.f012);//
		mFaceMap.put("[19]", R.drawable.f013);//
		mFaceMap.put("[50]", R.drawable.f014);//
		mFaceMap.put("[75]", R.drawable.f015);//
		mFaceMap.put("[71]", R.drawable.f016);//
		mFaceMap.put("[56]", R.drawable.f017);//
		mFaceMap.put("[22]", R.drawable.f018);//
		mFaceMap.put("[03]", R.drawable.f019);//
		mFaceMap.put("[07]", R.drawable.f020);//

		mFaceMap.put("[05]", R.drawable.f021);//
		mFaceMap.put("[20]", R.drawable.f022);//
		mFaceMap.put("[01]", R.drawable.f023);//
		mFaceMap.put("[12]", R.drawable.f024);//
		mFaceMap.put("[11]", R.drawable.f025);//
		mFaceMap.put("[27]", R.drawable.f026);//
		mFaceMap.put("[18]", R.drawable.f027);//
		mFaceMap.put("[67]", R.drawable.f028);//
		mFaceMap.put("[66]", R.drawable.f029);//
		mFaceMap.put("[23]", R.drawable.f030);//
		mFaceMap.put("[24]", R.drawable.f031);
		mFaceMap.put("[16]", R.drawable.f032);
		mFaceMap.put("[15]", R.drawable.f033);
		mFaceMap.put("[33]", R.drawable.f034);
		mFaceMap.put("[09]", R.drawable.f035);
		mFaceMap.put("[53]", R.drawable.f036);
		mFaceMap.put("[29]", R.drawable.f037);
		mFaceMap.put("[91]", R.drawable.f038);
		mFaceMap.put("[37]", R.drawable.f039);
		mFaceMap.put("[02]", R.drawable.f040);
		mFaceMap.put("[52]", R.drawable.f041);

		mFaceMap.put("[31]", R.drawable.f042);
		mFaceMap.put("[04]", R.drawable.f043);
		mFaceMap.put("[47]", R.drawable.f044);
		mFaceMap.put("[79]", R.drawable.f045);
		mFaceMap.put("[45]", R.drawable.f046);
		mFaceMap.put("[92]", R.drawable.f047);
		mFaceMap.put("[49]", R.drawable.f048);
		mFaceMap.put("[35]", R.drawable.f049);
		mFaceMap.put("[30]", R.drawable.f050);
		mFaceMap.put("[55]", R.drawable.f051);
		mFaceMap.put("[80]", R.drawable.f052);
		mFaceMap.put("[81]", R.drawable.f053);
		mFaceMap.put("[82]", R.drawable.f054);
		mFaceMap.put("[83]", R.drawable.f055);
		mFaceMap.put("[84]", R.drawable.f056);
		mFaceMap.put("[65]", R.drawable.f057);
		mFaceMap.put("[62]", R.drawable.f058);
		mFaceMap.put("[69]", R.drawable.f059);
		mFaceMap.put("[57]", R.drawable.f060);
		mFaceMap.put("[58]", R.drawable.f061);
		mFaceMap.put("[74]", R.drawable.f062);

		mFaceMap.put("[85]", R.drawable.f063);
		mFaceMap.put("[90]", R.drawable.f064);
		mFaceMap.put("[88]", R.drawable.f065);
		mFaceMap.put("[61]", R.drawable.f066);
		// mFaceMap.put("[钱]", R.drawable.f067);
		mFaceMap.put("[76]", R.drawable.f068);
		// mFaceMap.put("[美女]", R.drawable.f069);
		mFaceMap.put("[72]", R.drawable.f070);
		mFaceMap.put("[94]", R.drawable.f071);
		mFaceMap.put("[87]", R.drawable.f072);
		mFaceMap.put("[86]", R.drawable.f073);
		mFaceMap.put("[68]", R.drawable.f074);
		mFaceMap.put("[77]", R.drawable.f075);
		mFaceMap.put("[78]", R.drawable.f076);
		mFaceMap.put("[73]", R.drawable.f077);
		mFaceMap.put("[38]", R.drawable.f078);
		mFaceMap.put("[100]", R.drawable.f079);
		mFaceMap.put("[70]", R.drawable.f080);
		mFaceMap.put("[25]", R.drawable.f081);
		mFaceMap.put("[26]", R.drawable.f082);
		mFaceMap.put("[32]", R.drawable.f083);

		mFaceMap.put("[36]", R.drawable.f084);
		mFaceMap.put("[42]", R.drawable.f085);
		mFaceMap.put("[43]", R.drawable.f086);
		mFaceMap.put("[44]", R.drawable.f087);
		mFaceMap.put("[46]", R.drawable.f088);
		mFaceMap.put("[48]", R.drawable.f089);
		mFaceMap.put("[51]", R.drawable.f090);
		mFaceMap.put("[54]", R.drawable.f091);
		mFaceMap.put("[59]", R.drawable.f092);
		mFaceMap.put("[60]", R.drawable.f093);
		mFaceMap.put("[89]", R.drawable.f094);
		mFaceMap.put("[93]", R.drawable.f095);
		mFaceMap.put("[95]", R.drawable.f096);
		mFaceMap.put("[96]", R.drawable.f097);
		mFaceMap.put("[97]", R.drawable.f098);
		mFaceMap.put("[98]", R.drawable.f099);
		// mFaceMap.put("[105]", R.drawable.f100);
		// mFaceMap.put("[101]", R.drawable.f101);
		// mFaceMap.put("[102]", R.drawable.f102);
		// mFaceMap.put("[103]", R.drawable.f103);
		// mFaceMap.put("[104]", R.drawable.f104);

		mFaceMap.put("[99]", R.drawable.f105);
		mFaceMap.put("[08]", R.drawable.f106);
	}

	public Map<String, Integer> getmFaceMap() {
		if (!mFaceMap.isEmpty())
			return mFaceMap;
		return null;
	}

}
