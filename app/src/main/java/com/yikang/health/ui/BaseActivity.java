package com.yikang.health.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.cache.DataCache;
import com.yikang.health.widget.dialog.CustomProgressLoad;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public abstract class BaseActivity extends FragmentActivity {

	protected int layoutResID;
	protected LayoutInflater inflater;
	protected int width = 0;
	protected int height = 0;
	protected int titleBarHeight;
	private CustomProgressLoad progressDialog;
	protected YIKApplication application = YIKApplication.getContext();
	protected CompositeSubscription subscription = new CompositeSubscription();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		//透明状态栏  
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {  
//            // Translucent status bar 
//            //透明状态栏  
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//            //透明导航栏  
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//        }  
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();// 屏幕宽度
		height = wm.getDefaultDisplay().getHeight();
		DataCache.getDataCache().addRuningActivity(this);
		initIntent();
		setContentView(layoutResID);
		inflater = LayoutInflater.from(this);
		initSystemBar();
		initBarHeight();
		// activity自定义初始化工作
		initControl();

		initObserver();
	}

	/**
     * 状态栏颜色设置
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
	protected void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }
    protected View view;
    /**
     * 状态栏设置高度
     */
    protected final void initBarHeight() {
        view = this.findViewById(R.id.idr_StatusBar);
        if (view != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.getLayoutParams().height = getStatusBarHeight();
            view.requestFocus();
        }
    }
    protected void setBarColor(int resId) {
		// TODO Auto-generated method stub
    	if (view != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setBackgroundColor(resId);
        }
	}
    /**
     * 获取状态栏的高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
	/**
	 * @see Activity#onWindowFocusChanged(boolean)
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Rect rect = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		titleBarHeight = rect.top;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initVariable();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		saveData();
	}

	/**
	 * 在此方法中处理上个页面传入的数据
	 */
	abstract public void initIntent();

	/**
	 * 初始化控件，监听方法建议使用finfviewbyid(id).setOCL(myOCL)的方法。
	 */

	public void initControl() {
		RelativeLayout layoutTop = (RelativeLayout) findViewById(R.id.lay_top_title);
		if (layoutTop != null) {
			TextView mTitle = (TextView) findViewById(R.id.txt_top_title);
			mSetTitleText(mTitle);
			View buttonLeft = findViewById(R.id.btn_top_left);
			if (buttonLeft != null) {
				buttonLeft.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						leftButton(arg0);
					}
				});
			}

			// View buttonRight = findViewById(R.id.btn_top_right);
			// if (buttonRight != null) {
			// buttonRight.setOnClickListener(new OnClickListener() {
			// @Override
			// public void onClick(View arg0) {
			// rightButton(arg0);
			// }
			// });
			// }
			//
			// View tvRight = findViewById(R.id.tv_top_right);
			// if (tvRight != null) {
			// tvRight.setOnClickListener(new OnClickListener() {
			// @Override
			// public void onClick(View arg0) {
			// rightTextView(arg0);
			// }
			// });
			// }
		}
	}

	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param arg0
	 */
	protected void leftButton(View arg0) {
		// arg0.getBackground().setAlpha(127);
		finish();
	}

	/**
	 * 初始化各个控件显示内容 因情况，条件不同，故放在initIntent()之后，依据initIntent()中获得的数据初始化显示内容
	 */
	abstract public void initVariable();

	/**
	 * 初始化各种动态变化控件的各种观察者。 状态不会改变的不需要变量名，在initControl()是进行setOCL();
	 */
	abstract public void initObserver();

	/**
	 * 存储数据方法，在onPause中自动调用
	 */
	abstract public void saveData();

	abstract public void backBtnFunction();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			backBtnFunction();
		}
		return super.onKeyDown(keyCode, event);
	}

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
	 * @param bundle
	 *            要传递的参数
	 */
	protected void openActivity(Class<?> pClass, Bundle bundle) {
		Intent intent = new Intent(this, pClass);
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
		// ImageLoader.getInstance().clearDiscCache();
//		ImageLoader.getInstance().clearMemoryCache();
//		Intent intent = new Intent(YIKApplication.getContext(),
//				QpNewsMainActivity.class);
//		now.startActivity(intent);
//		now.finish();
	}

	/**
	 * 获取当前时间long
	 */
	public long getLongTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 自定义吐司
	 * 
	 * @param msg
	 */
	public void ToastShow(final String msg) {

		BaseActivity.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				DataCache.getDataCache().getToast(msg).show();
			}
		});
	}

	/**
	 * CancelToast
	 */
	public void CancelToast() {
		BaseActivity.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				DataCache.getDataCache().CancleToast();

			}
		});
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
	protected void add(Subscription sub) {
		if (this.subscription != null && sub != null) {
			this.subscription.add(sub);
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (this.subscription != null && !this.subscription.isUnsubscribed()) {
			this.subscription.unsubscribe();
		}
		System.gc();
	}
}
