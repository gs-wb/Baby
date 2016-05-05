package com.yikang.health.ui.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.MainAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.TodayReadInfo;
import com.yikang.health.ui.BaseFragment;

public class BabyFragment extends BaseFragment implements OnClickListener ,TaskExpandListener{
	private SlideMenu slideMenu;
	public BabyFragment(){
		super();
		layoutResID = R.layout.fragment_main_baby_layout;
	}
	public BabyFragment(SlideMenu slideMenu) {
		super();
		layoutResID = R.layout.fragment_main_baby_layout;
		this.slideMenu = slideMenu;
	}

	@Override
	protected void mSetTitleText(TextView mTitle) {
		// TODO Auto-generated method stub
		super.mSetTitleText(mTitle);
		mTitle.setText("宝贝");
	}
	@Override
	public void initControl(View v) {
		// 初始化组件
		initComponent(v);
		laodData();
	}

	@Override
	public void initObserver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}

	// 4月1日,怀孕44周，0天后出生
	private TextView tvPregnantMsg;
	private ListView lvTodayRead;
	private MainAdapter mainAdapter;
	private List<TodayReadInfo> readInfos = new ArrayList<TodayReadInfo>();

	/**
	 * 初始化组件
	 */
	private void initComponent(View v) {
		ImageView menuImg = (ImageView) v.findViewById(R.id.btn_top_left);
		menuImg.setImageResource(R.drawable.nemu);
		menuImg.setOnClickListener(this);
		tvPregnantMsg = (TextView) v.findViewById(R.id.tv_pregnant_msg);
		lvTodayRead = (ListView) v.findViewById(R.id.lv_today_read);
		mainAdapter = new MainAdapter(getActivity());
		lvTodayRead.setAdapter(mainAdapter);

		LayoutInflater lif = (LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View headerView = lif.inflate(R.layout.baby_list_head_layout, null);
		lvTodayRead.addHeaderView(headerView);

	}

	private void laodData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 6; i++) {
			readInfos.add(new TodayReadInfo("", "", 1, ""));
		}
		mainAdapter.setData(readInfos);
//		YIKApplication.client.getNoticeList(getActivity(),"1",this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_top_left) {
			if (slideMenu.isMainScreenShowing()) {
				slideMenu.openMenu();
			} else {
				slideMenu.closeMenu();
			}
		}
	}

	@Override
	public <T> void onTaskCompleted(String resultCode, T result, int connId) {
		if(resultCode.equals(Constants.RESULT_SUCCESS)){
			switch (connId) {
			case Constants.GET_NOTICE_LIST:
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
