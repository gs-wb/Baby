package com.yikang.health.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.StoryAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.Mp3Info;
import com.yikang.health.server.GsonTools;
import com.yikang.health.ui.BaseFragment;
import com.yikang.health.ui.story.StoryDetailActivity;
import com.yikang.health.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryFragment extends BaseFragment implements OnItemClickListener, TaskExpandListener {

    private LinearLayout ll_grid;

    private List<Mp3Info> storyList = new ArrayList<Mp3Info>();

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
        ll_grid = (LinearLayout) v.findViewById(R.id.ll_grid);
//        gvStory = (GridView) v.findViewById(R.id.gv_story);
//        storyAdapter = new StoryAdapter(getActivity());
//        gvStory.setAdapter(storyAdapter);
        loadData();
    }

    @Override
    public void initObserver() {

    }

    private void loadData() {
        YIKApplication.client.getMp3List(getActivity(), "1", this);
//		YIKApplication.client.getVideoList(getActivity(), "1", this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent(getActivity(), StoryDetailActivity.class);
        intent.putExtra("title",keys.get(position));
        startActivity(intent);
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if (resultCode.equals(Constants.RESULT_SUCCESS)) {
            switch (connId) {
                case Constants.GET_MP3_LIST:
//                    storyList = GsonTools.getList(result.toString(), Mp3Info.class);
//                    if (storyList != null) {
//                        if (storyList.size() >= 6)
//                            storyAdapter.setData(storyList.subList(0, 6));
//                        else storyAdapter.setData(storyList);
//                    }
                    ToastShow(result.toString());
                    String str = Utils.getFromAssets("data.txt");
                    storyList = GsonTools.getList(str, Mp3Info.class);
                    classStory();
                    break;
                case Constants.GET_VIDEO_LIST:
                    ToastShow(result.toString());
                    break;
            }
        } else {
            ToastShow(result.toString());
        }
    }
    List<String> keys = new ArrayList<String>();
    private void classStory() {
        if (storyList != null && !storyList.isEmpty()) {
            for (Mp3Info mp3 : storyList) {
                if(!keys.contains(mp3.getFile_name())){
                    keys.add(mp3.getFile_name());
                }
            }
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.story_gridview_layout,null);
            ll_grid.addView(v);
            GridView gvStory = (GridView)v.findViewById(R.id.gv_story);
            StoryAdapter storyAdapter = new StoryAdapter(getActivity());
            gvStory.setAdapter(storyAdapter);
            storyAdapter.setData(keys);
            gvStory.setOnItemClickListener(this);
        }
    }
//    private void classStory() {
//        if (storyList != null && !storyList.isEmpty()) {
//            List<String> keys = new ArrayList<String>();
//            for (Mp3Info mp3 : storyList) {
//                if(!keys.contains(mp3.getFile_name())){
//                    keys.add(mp3.getFile_name());
//                }
//            }
//            for (String key : keys) {
//                List<Mp3Info> storyTemp = new ArrayList<Mp3Info>();
//                for (Mp3Info mp3 : storyList) {
//                    if(key.equals(mp3.getFile_name())){
//                        storyTemp.add(mp3);
//                    }
//                }
//                View v = LayoutInflater.from(getActivity()).inflate(R.layout.story_gridview_layout,null);
//                ll_grid.addView(v);
//                TextView story_class = (TextView)v.findViewById(R.id.story_class);
//                GridView gvStory = (GridView)v.findViewById(R.id.gv_story);
//                story_class.setText(storyTemp.isEmpty()?"":storyTemp.get(0).getFile_name());
//                StoryAdapter storyAdapter = new StoryAdapter(getActivity());
//                gvStory.setAdapter(storyAdapter);
//                storyAdapter.setData(storyTemp);
//            }
//        }
//    }

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
