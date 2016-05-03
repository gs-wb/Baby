package com.yikang.health.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.cache.DataCache;
import com.yikang.health.widget.dialog.CustomProgressLoad;
/**
 * base Fragment
 * @author hh
 *
 */
public abstract class BaseFragment extends Fragment {
	public int layoutResID;
	protected int width = 0;
	protected int height = 0;
	private CustomProgressLoad progressDialog;
	public YIKApplication application = YIKApplication.getContext();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		WindowManager wm = (WindowManager) getActivity().getSystemService(
				Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();// 屏幕宽度
		height = wm.getDefaultDisplay().getHeight();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		View view = null;
		try {
			view = inflater.inflate(layoutResID,null);
			if(view != null){
				TextView mTitle = (TextView) view.findViewById(R.id.txt_top_title);
				mSetTitleText(mTitle);
				initControl(view);
				initObserver();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		initVariable();		
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		saveData();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.gc();
//		backBtnFunction();
	}
	
	

	/**
	 * 在此方法中处理上个页面传入的数据
	 */
//	abstract public void initIntent();
	
	/**
	 * 初始化各个控件
	 * @param view 
	 */
	abstract public void initControl(View view);
	
	/**
	 * 初始化各个控件显示内容 因情况，条件不同，故放在initIntent()之后，依据initIntent()中获得的数据初始化显示内容
	 */
//	abstract public void initVariable();

	/**
	 * 初始化各种动态变化控件的各种观察者。 状态不会改变的不需要变量名，在initControl()是进行setOCL();
	 */
	abstract public void initObserver();
	
	/**
	 * 存储数据方法，在onPause中自动调用
	 */
//	abstract public void saveData();
	
//	abstract public void backBtnFunction();
	
	/**
	 * 通过类名启动activity
	 * 
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	
	/**
	 * 通过类名启动activity
	 * 
	 * @param pClass
	 *            要启动的类
	 * @param pBundle
	 *            要传递的参数
	 */
	protected void openActivity(Class<?> pClass, Bundle bundle) {
		Intent intent = new Intent(getActivity(), pClass);
		if (bundle != null) {
			intent.putExtra("bundle", bundle);
		}
		startActivity(intent);
	}

	/**
	 * 通过action 启动
	 * 
	 * @param action
	 *            action的名称
	 */
	protected void openActivity(String action) {
		openActivity(action, null);
	}

	/**
	 * 通过action 启动
	 * 
	 * @param action
	 *            action的名称
	 * @param pBundle
	 *            传递的参数
	 */
	protected void openActivity(String action, Bundle pBundle) {
		Intent intent = new Intent(action);
		if (pBundle != null) {
			intent.putExtra("bundle", pBundle);
		}
		startActivity(intent);
	}
	
	/***
	 * 跳转到主界面
	 */
	public void backToHome(Activity now) {

//		Intent intent = new Intent(YIKApplication.getContext(), LotteryMainActivity.class);
//		now.startActivity(intent);
//		now.finish();
	}
	
	
	/**
	 * 获取当前时间long 
	 */
	public long getLongTime(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 自定义吐司
	 * 
	 * @param msg
	 */
	public void ToastShow(final String msg) {

		DataCache.getDataCache().getToast(msg).show();
	}
	
	/**
	 * CancelToast
	 */
	public void CancelToast(){
		
		DataCache.getDataCache().CancleToast();
	}
	
	
	public void showProgressDialog(final Context context){
		if (progressDialog == null) {
			progressDialog = CustomProgressLoad.createDialog(context);
		}
		progressDialog.show();
	}
	
	public void showProgressDialog(final Context context,final String message){
		if (progressDialog == null) {
			progressDialog = CustomProgressLoad.createDialog(context);
			progressDialog.setMessage(message);
		}
		progressDialog.show();
	}
	
	public void closeDialog(){
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		
	}
	
}
