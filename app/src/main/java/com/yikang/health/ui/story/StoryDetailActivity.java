package com.yikang.health.ui.story;

import android.view.LayoutInflater;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/6.
 */
public class StoryDetailActivity extends BaseActivity implements View.OnClickListener, TaskExpandListener {
    private ListView mListView;
    private StoryDetailListAdapter mAdapter;
    private TextView tvSize,tvStoryName;
    public List<Mp3Info> storyList = new ArrayList<Mp3Info>();
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
        loadData();
    }

    private void loadData() {
        YIKApplication.client.getMp3List(this, book_id, this);
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if (resultCode.equals(Constants.RESULT_SUCCESS)) {
            switch (connId) {
                case Constants.GET_MP3_LIST:
                    storyList = GsonTools.getList(result.toString(), Mp3Info.class);
                    if (storyList != null && !storyList.isEmpty()) {
                        tvStoryName.setText(storyList.get(0).getMp3_name());
                        tvSize.setText("共" + storyList.size() + "条");
                        mAdapter.setList(storyList);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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