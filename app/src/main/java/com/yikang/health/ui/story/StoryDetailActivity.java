package com.yikang.health.ui.story;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.StoryDetailListAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.Mp3Info;
import com.yikang.health.server.GsonTools;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.MyComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2016/5/6.
 */
public class StoryDetailActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener, TaskExpandListener {
    private ListView mListView;
    private StoryDetailListAdapter mAdapter;
    private TextView tvSize,tvStoryName;
    public ArrayList<Mp3Info> storyList = new ArrayList<Mp3Info>();
    private Mp3Info currMp3Info;
    public static StoryDetailActivity instance;

    public StoryDetailActivity() {
        super();
        instance = this;
        layoutResID = R.layout.activity_story_detail_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText(title);
    }

    String title = "";
    String book_id = "1";

    @Override
    public void initIntent() {
        title = getIntent().getStringExtra("title");
        book_id = getIntent().getStringExtra("book_id");
    }

    @Override
    public void initControl() {
        super.initControl();
        View v = LayoutInflater.from(this).inflate(R.layout.story_detail_head_layout,null);
        tvStoryName  = (TextView) v.findViewById(R.id.tv_story_name);
        tvSize = (TextView) v.findViewById(R.id.tv_size);
        mListView = (ListView) findViewById(R.id.lv_story);
        mListView.addHeaderView(v);
        mAdapter = new StoryDetailListAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        loadData();
    }

    private void loadData() {
        YIKApplication.client.getMp3List(this,"1", book_id, this);
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if (resultCode.equals(Constants.RESULT_SUCCESS)) {
            switch (connId) {
                case Constants.GET_MP3_LIST:
                    storyList = (ArrayList)GsonTools.getList(result.toString(), Mp3Info.class);
                    if (storyList != null && !storyList.isEmpty()) {
                        parseStory();
                        mAdapter.setList(storyList);
                        currMp3Info = storyList.get(0);
                        setCurrMp3();
                        tvSize.setText("共" + storyList.size() + "条");
                    }
                    break;
                case Constants.GET_VIDEO_LIST:
                    ToastShow(result.toString());
                    break;
            }
        } else {
            ToastShow(result.toString());
        }
    }
    private void parseStory(){
        for (Mp3Info mp3:storyList){
            String name = mp3.getMp3_name();
            String[] strs = name.split("---");
            mp3.setMp3_name(strs[strs.length - 1].replace(".mp3", ""));
            mp3.setId(mp3.getMp3_name().replaceAll("\\D", ""));
            mp3.setMp3_name(mp3.getMp3_name().replaceAll(mp3.getId(), "").replaceFirst("-", ""));
        }
        Collections.sort(storyList, new MyComparator());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0){
            currMp3Info = storyList.get(position-1);
            setCurrMp3();
            Intent intent = new Intent(this,PlayerActivity.class);
//            intent.putExtra("storyList",storyList);
            intent.putExtra("mp3Infos",storyList);
            intent.putExtra("position",position-1);
            startActivity(intent);
        }
    }

    private void setCurrMp3(){
        if(currMp3Info!=null){
            tvStoryName.setText(currMp3Info.getMp3_name());
        }
    }
    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {
    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }

    @Override
    public void onTaskCanceled() {

    }

    @Override
    public void onTaskError(String resultCode, int conId, String msg) {

    }
}