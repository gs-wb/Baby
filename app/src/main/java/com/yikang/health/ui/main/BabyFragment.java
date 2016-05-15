package com.yikang.health.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.MainAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class BabyFragment extends BaseFragment implements OnClickListener ,TaskExpandListener{
	private SlideMenu slideMenu;
	public BabyFragment(){
		super();
		layoutResID = R.layout.fragment_main_baby_layout;
	}
	@SuppressLint("ValidFragment")
	public BabyFragment(SlideMenu slideMenu) {
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
	private List<BabyLoreModel> babyLoreList = new ArrayList<BabyLoreModel>();

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
//		for (int i = 0; i < 6; i++) {
//			readInfos.add(new BabyLoreModel("", "", 1, ""));
//		}
		mainAdapter.setData(babyLoreList);
		YIKApplication.client.getBabyLoresByGet(getActivity(), this);
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
		switch (connId) {
		case Constants.GET_BABYLORE_DATA:
//			ToastShow(result.toString());
			break;
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
