package com.yikang.health.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.Utils;

/**
 * 登录页
 * 
 * @author zwb
 * 
 */
public class ForgetActivity extends BaseActivity implements OnClickListener ,TaskExpandListener{

	private Button btnYzm, btnNext;
	private EditText etPhone, etYzm;

	public ForgetActivity() {
		// TODO Auto-generated constructor stub
		layoutResID = R.layout.activity_forget_psd;
	}

	@Override
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub
		super.mSetTitleText(mTitle);
		mTitle.setText("找回密码");
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initControl() {
		// TODO Auto-generated method stub
		super.initControl();
		btnYzm = (Button) findViewById(R.id.button_yzm);
		btnNext = (Button) findViewById(R.id.button_next);
		etPhone = (EditText) findViewById(R.id.edit_tellphone);
		etYzm = (EditText) findViewById(R.id.edit_yzm);
		btnYzm.setOnClickListener(this);
		btnNext.setOnClickListener(this);
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
		case R.id.button_yzm:
			if(!TextUtils.isEmpty(etPhone.getText()) && 
					Utils.checkSMSvaildateAndPhone(etPhone.getText().toString())){
				YIKApplication.client.addCode(this,etPhone.getText().toString(),this);
			}else{
				ToastShow("手机号码输入有误！");
			}
			break;
		case R.id.button_next:
			if(TextUtils.isEmpty(etPhone.getText()) && 
					!Utils.checkSMSvaildateAndPhone(etPhone.getText().toString())){
				ToastShow("手机号码输入有误！");
				return;
			}
			if(TextUtils.isEmpty(etYzm.getText())){
				ToastShow("验证码不能为空！");
				return;
			}
			Intent intent = new Intent(this,null);
			intent.putExtra("phone", etPhone.getText().toString());
			intent.putExtra("yzm", etYzm.getText().toString());
			startActivity(new Intent());
			break;
		}
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backBtnFunction() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void onTaskCompleted(String resultCode, T result, int connId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskCanceled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskError(String resultCode, int conId, String msg) {
		// TODO Auto-generated method stub
		
	}

}
