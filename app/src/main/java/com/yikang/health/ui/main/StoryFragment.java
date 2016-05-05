package com.yikang.health.ui.main;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.StoryAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.KnowledgeSubInfo;
import com.yikang.health.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends BaseFragment implements OnItemClickListener ,TaskExpandListener {

	private GridView gvStory;

	private StoryAdapter storyAdapter;

	private List<String> storyList = new ArrayList<String>();

	public StoryFragment() {
		super();
		 layoutResID = R.layout.fragment_main_story_layout;
	}
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}
	@Override
	protected void mSetTitleText(TextView mTitle) {
		super.mSetTitleText(mTitle);
		mTitle.setText("故事");
	}
	@Override
	public void initControl(View v) {
		gvStory = (GridView)v.findViewById(R.id.gv_story);
		storyAdapter = new StoryAdapter(getActivity());

		gvStory.setAdapter(storyAdapter);
		loadData();
	}
	@Override
	public void initObserver() {

	}

	private void loadData() {
		for (int i = 0; i < 10; i++) {
			storyList.add(""+i);
		}
		storyAdapter.setData(storyList);
		YIKApplication.client.getMp3List(getActivity(), "1", this);
		YIKApplication.client.getVideoList(getActivity(), "1", this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}
	@Override
	public <T> void onTaskCompleted(String resultCode, T result, int connId) {
		if(resultCode.equals(Constants.RESULT_SUCCESS)){
			switch (connId) {
				case Constants.GET_MP3_LIST:
					ToastShow(result.toString());
					break;
				case Constants.GET_VIDEO_LIST:
					ToastShow(result.toString());
					break;
			}
		}else{
			ToastShow(result.toString());
		}
	}

	@Override
	public void onTaskError(String resultCode, int conId, String msg) {

	}

	@Override
	public void onDestroy() {
	}


	@Override
	public void onTaskCanceled() {

	}
}
