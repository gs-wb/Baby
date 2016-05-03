package com.yikang.health.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.Utils;

/**
 * 注册页
 * 
 * @author zwb
 * 
 */
public class RegistActivity extends BaseActivity implements OnClickListener ,TaskExpandListener{
	private Button btnYzm, btnReg;
	private EditText etPhone,etYzm,etUsername,etPsd,etPsd2;

	public RegistActivity() {
		super();
		layoutResID = R.layout.activity_regist;
	}

	@Override
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub
		super.mSetTitleText(mTitle);
		mTitle.setText("注册");
	}

	@Override
	public void initIntent() {
		// TODO Auto-generated method stub

	}
	@Override
	public void initControl() {
		super.initControl();
		etPhone = (EditText)findViewById(R.id.edit_tellphone);
		etYzm = (EditText)findViewById(R.id.edit_yzm);
		etUsername = (EditText)findViewById(R.id.edit_user_name);
		etPsd = (EditText)findViewById(R.id.edit_psd);
		etPsd2 = (EditText)findViewById(R.id.edit_psd2);
		btnYzm = (Button)findViewById(R.id.button_yzm);
		btnReg = (Button)findViewById(R.id.button_reg);
		
		btnYzm.setOnClickListener(this);
		btnReg.setOnClickListener(this);
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
		switch (v.getId()) {
		case R.id.button_yzm:
			if(!TextUtils.isEmpty(etPhone.getText()) && 
					Utils.checkSMSvaildateAndPhone(etPhone.getText().toString())){
				YIKApplication.client.addCode(this,etPhone.getText().toString(),this);
			}else{
				ToastShow("手机号码输入有误！");
			}
			break;
		case R.id.button_reg:
			if(TextUtils.isEmpty(etPhone.getText()) && 
					!Utils.checkSMSvaildateAndPhone(etPhone.getText().toString())){
				ToastShow("手机号码输入有误！");
				return;
			}
			if(TextUtils.isEmpty(etYzm.getText())){
				ToastShow("验证码不能为空！");
				return;
			}
			if(TextUtils.isEmpty(etUsername.getText())){
				ToastShow("用户名不能为空！");
				return;
			}
			if(TextUtils.isEmpty(etPsd.getText())){
				ToastShow("密码不能为空！");
				return;
			}
			if(TextUtils.isEmpty(etPsd2.getText()) || !etPsd.getText().toString().equals(etPsd2.getText().toString())){
				ToastShow("两次密码输入不一致！");
				return;
			}
			YIKApplication.client.addUser(this,etUsername.getText().toString(),
					etPsd.getText().toString(),
					etYzm.getText().toString(),this);
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
		if(resultCode.equals(Constants.RESULT_SUCCESS)){
			switch (connId) {
			case Constants.ADD_CODE:
				ToastShow(result.toString());
				break;
			case Constants.ADD_USER:
				ToastShow(result.toString());
				break;
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

}
