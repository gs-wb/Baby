package com.yikang.health.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.ui.main.MainTabActivity;
import com.yikang.health.utils.Utils;

/**
 * 登录页
 * @author zwb
 *
 */
public class MessageLoginActivity extends BaseActivity implements OnClickListener,TaskExpandListener{

	private Button btnLogin,btnPsdLogin,btnYzm;
	private EditText etTellphone,etYzm;
	public MessageLoginActivity() {
		// TODO Auto-generated constructor stub
		layoutResID = R.layout.activity_message_login;
	}
	@Override
	public void initIntent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initControl() {
		// TODO Auto-generated method stub
		super.initControl();
		btnLogin = (Button)findViewById(R.id.button_login);
		btnPsdLogin = (Button)findViewById(R.id.btn_psd_login);
		btnYzm = (Button)findViewById(R.id.btn_yzm);
		etTellphone = (EditText)findViewById(R.id.edit_tellphone);
		etYzm = (EditText)findViewById(R.id.edit_yzm);
		btnLogin.setOnClickListener(this);
		btnPsdLogin.setOnClickListener(this);
		btnYzm.setOnClickListener(this);
	}
	@Override
	public void initVariable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initObserver() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_login:
			startActivity(new Intent(this, MainTabActivity.class));
			break;
		case R.id.btn_yzm:
			if(!TextUtils.isEmpty(etTellphone.getText()) && 
					Utils.checkSMSvaildateAndPhone(etTellphone.getText().toString())){
				YIKApplication.client.addCode(this,etTellphone.getText().toString(),this);
			}else{
				ToastShow("手机号码输入有误！");
			}
			break;
		case R.id.btn_psd_login:
			startActivity(new Intent(this, LoginActivity.class));
			break;
		}
	}
	
	@Override
	public <T> void onTaskCompleted(String resultCode, T result, int connId) {
		if(resultCode.equals(Constants.RESULT_SUCCESS)){
			if(connId == Constants.ADD_CODE){
				ToastShow(result.toString());
			}
		}else{
			ToastShow(result.toString());
		}
	}
	@Override
	public void onTaskCanceled() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTaskError(String resultCode, int conId, String msg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backBtnFunction() {
		// TODO Auto-generated method stub
		
	}
}
