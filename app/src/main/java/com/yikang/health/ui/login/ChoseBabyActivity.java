package com.yikang.health.ui.login;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.ui.main.MainTabActivity;

/**
 * 登录页
 * @author zwb
 *
 */
public class ChoseBabyActivity extends BaseActivity implements OnClickListener{

	private ImageView ivNotBorn0,ivNotBorn,ivBorn;
	public static ChoseBabyActivity instance;
	public ChoseBabyActivity() {
		super();
		layoutResID = R.layout.activity_chose_baby;
		instance = this;
	}
	@Override
	public void initIntent() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub
		super.mSetTitleText(mTitle);
		mTitle.setText("选择宝宝");
	}
	@Override
	public void initControl() {
		// TODO Auto-generated method stub
		super.initControl();
		ivNotBorn0 = (ImageView)findViewById(R.id.iv_not_born0);
		ivNotBorn = (ImageView)findViewById(R.id.iv_not_born);
		ivBorn = (ImageView)findViewById(R.id.iv_born);
		ivNotBorn0.setOnClickListener(this);
		ivNotBorn.setOnClickListener(this);
		ivBorn.setOnClickListener(this);
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
		int type = 0;
		switch (v.getId()) {
		case R.id.iv_not_born0:
			type = 0;
			break;
		case R.id.iv_not_born:
			type = 1;
			break;
		case R.id.iv_born:
			type = 2;
			break;
		}
		Intent intent = new Intent(this, PrePregnantActivity.class);
		intent.putExtra("type", type);
		startActivity(intent);
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
	protected void onDestroy() {
		super.onDestroy();
		instance = null;
	}
}
