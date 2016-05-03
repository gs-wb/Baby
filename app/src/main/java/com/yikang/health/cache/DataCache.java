package com.yikang.health.cache;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.widget.Toast;

import com.yikang.health.YIKApplication;
import com.yikang.health.model.UserInfo;
import com.yikang.health.widget.CustomToast;
/**
 * 
 * @author hh
 *
 */
public class DataCache {
	private UserInfo user;
	private CustomToast toast;
	static DataCache lotteryDataCache;

	/** 页面回退栈 **/
	LinkedList<Integer> activityStack;
	ArrayList<Activity> activityListNow;
	LinkedList<Activity> activityListAll;

	public DataCache() {
		// TODO Auto-generated constructor stub
		activityStack = new LinkedList<Integer>();
		activityListNow = new ArrayList<Activity>();
		activityListAll = new LinkedList<Activity>();
	}

	public static DataCache getDataCache() {
		if (lotteryDataCache == null)
			lotteryDataCache = new DataCache();

		return lotteryDataCache;

	}
	

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public CustomToast getToast(String text) {
		if (toast == null) {
			toast = CustomToast.makeText(
					YIKApplication.getContext(), text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		return toast;
	}

	public void CancleToast() {
		if (toast != null) {
			toast.cancel();
		}
	}

	public void setToast(CustomToast t) {
		toast = t;
	}

	/**
	 * 入栈新activity
	 * 
	 * @param activityNow
	 */
	public void putActivity(int activityNow) {
		activityStack.add(activityNow);
	}

	/**
	 * activity 回退出栈
	 * 
	 * @param activityNow
	 * @return
	 */
	public int getActivity(int activityNow) {

		for (int i = activityStack.size() - 1; i >= 0; i--) {
			if (activityStack.get(i).intValue() == activityNow) {
				// 找到对应位置，自身及后面的全部移除

			}
		}
		return 0;
	}

	/**
	 * 
	 * @param activity
	 */

	public void addRuningActivity(Activity activity) {
		activityListNow.add(activity);
	}

	public  ArrayList<Activity> getActivityNowList(){
		 return activityListNow;
	}
	/**
	 * 清除所有运行的activity
	 */

	public void cleanRuningActivitys() {
		for (int i = activityListNow.size() - 1; i >= 0; i--) {
			activityListNow.get(i).finish();
		}
		activityListNow.clear();
	}
	
	public void removeRuningActivitys(Activity activity) {
		if(activityListNow.contains(activity))
			activityListNow.remove(activity);
	}
	
	public void clean(){
		this.user = null;
		activityStack = null;
		cleanRuningActivitys();
	}
}
