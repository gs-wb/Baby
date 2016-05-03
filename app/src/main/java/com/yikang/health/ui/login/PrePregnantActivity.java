package com.yikang.health.ui.login;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.ui.main.MainTabActivity;
import com.yikang.health.widget.time.DateViewUtils;

import java.util.Calendar;

/**
 * 备孕中
 * @author zwb
 *
 */
public class PrePregnantActivity extends BaseActivity implements OnClickListener{

	private Button btnConfirm;
	private TextView btn_1;
	private TextView btn_2;
	private TextView tvMsg;
	private int type=0;
	DateViewUtils dateViewUtils;
	public PrePregnantActivity() {
		layoutResID = R.layout.activity_pre_pregnant;
	}
	@Override
	public void initIntent() {
		type = getIntent().getIntExtra("type",0);
	}
	@Override
	protected void mSetTitleText(TextView mTitle) {
		super.mSetTitleText(mTitle);
		mTitle.setText("备孕中");
	}
	@Override
	public void initControl() {
		super.initControl();
		btnConfirm = (Button)findViewById(R.id.button_confirm);
		btn_1 = (TextView)findViewById(R.id.btn_1);
		btn_2 = (TextView)findViewById(R.id.btn_2);
		tvMsg = (TextView)findViewById(R.id.tv_baby_msg);
		TextView tvDate = (TextView) findViewById(R.id.tv_baby_date);
		ImageView ivState = (ImageView) findViewById(R.id.iv_born_state);
		LinearLayout llTime = ((LinearLayout) findViewById(R.id.ll_time_selector));
		btnConfirm.setOnClickListener(this);
		dateViewUtils = new DateViewUtils(this);

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		switch (type){
			case 0:
				ivState.setImageResource(R.drawable.baby_yunqian);
				btn_1.setText("月经持续天数");
				btn_2.setText("月经周期");
				tvDate.setText("6天");
				llTime.addView(dateViewUtils.initDateDayView(6, tvDate));
				break;
			case 1:
				ivState.setImageResource(R.drawable.baby_yunqian);
				btn_1.setText("输入预产期");
				btn_2.setText("计算预产期");
				tvDate.setText(year + "-" + month + "-" + day);
				llTime.addView(dateViewUtils.initFullDateView(tvDate));
				break;
			case 2:
				ivState.setImageResource(R.drawable.baby_chusheng);
				btn_1.setVisibility(View.GONE);
				btn_2.setText("宝宝出生日期");
				tvDate.setText(year + "-" + month + "-" + day);
				llTime.addView(dateViewUtils.initFullDateView(tvDate));
				break;
		}
	}

	
	
	@Override
	public void initVariable() {

	}

	@Override
	public void initObserver() {
		
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_confirm:
			if(ChoseBabyActivity.instance!=null){
				ChoseBabyActivity.instance.finish();
			}
			this.finish();
			break;
		}
	}
	@Override
	public void saveData() {

	}

	@Override
	public void backBtnFunction() {

	}

}
