package com.yikang.health.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.net.HttpHelper;
import com.yikang.health.server.WebDataUtils;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 登录页
 * @author zwb
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener,TaskExpandListener{

	private Button btnLogin,btnReg,btnForget;
	private EditText etUsername,etPassword;
	public LoginActivity() {
		// TODO Auto-generated constructor stub
		layoutResID = R.layout.activity_login;
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
		btnReg = (Button)findViewById(R.id.button_regester);
		btnForget = (Button)findViewById(R.id.btn_forget_psd);
		etUsername = (EditText)findViewById(R.id.edit_user_name);
		etPassword = (EditText)findViewById(R.id.edit_password);
		btnLogin.setOnClickListener(this);
		btnReg.setOnClickListener(this);
		btnForget.setOnClickListener(this);
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
//			if(!TextUtils.isEmpty(etUsername.getText()) &&
//					!TextUtils.isEmpty(etPassword.getText())){
				new AsyncTask<Void,Void,String>(){
					@Override
					protected String doInBackground(Void... params) {
						try{
							HashMap map = new HashMap();
//							{"username":"15221365460","password":""}
							map.put("username", "15221365460");
							map.put("psd", "123456");
							String msg = WebDataUtils.getInstance().jsonDataStr(map,"getUserLogin.html");
							return msg;
						}catch (Exception e){
							e.printStackTrace();
							return "";
						}

					}

					@Override
					protected void onPostExecute(String s) {
						super.onPostExecute(s);
						ToastShow(s);
					}
				}.execute();

//				YIKApplication.client.getUserLogin(this,etUsername.getText().toString(),
//						etPassword.getText().toString(),this);
//			}else{
//				ToastShow("用户名或密码不能为空");
//			}
			break;
		case R.id.button_regester:
			startActivity(new Intent(this, RegistActivity.class));
			break;
		case R.id.btn_forget_psd:
			startActivity(new Intent(this, ForgetActivity.class));
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
			if(connId == Constants.GET_USER_LOGIN){
				ToastShow(result.toString());
			}
		}else{
			ToastShow(result.toString());
		}
		startActivity(new Intent(this, ChoseBabyActivity.class));
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
