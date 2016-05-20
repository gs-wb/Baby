package com.yikang.health.ui.story;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.StoryDetailListAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.Mp3Info;
import com.yikang.health.server.GsonTools;
import com.yikang.health.server.JsonParser;
import com.yikang.health.service.PlayerService;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.MediaUtil;
import com.yikang.health.utils.MyComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2016/5/6.
 */
public class StoryDetailActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener, TaskExpandListener {
    private ListView mListView;
    public StoryDetailListAdapter mAdapter;
    private TextView tvSize,tvStoryName,tv_time;
    public ArrayList<Mp3Info> storyList = new ArrayList<Mp3Info>();
    private Mp3Info currMp3Info;
    private VoicePlayFragment playFragment;
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
        tv_time = (TextView) v.findViewById(R.id.tv_time);
        mListView = (ListView) findViewById(R.id.lv_story);

        mListView.addHeaderView(v);
        mAdapter = new StoryDetailListAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        loadData();

    }

    private void loadData() {
        YIKApplication.client.getMp3List(this, "1", book_id, this);
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if (resultCode.equals(Constants.RESULT_SUCCESS)) {
            switch (connId) {
                case Constants.GET_MP3_LIST:
                    storyList = (ArrayList<Mp3Info>) JsonParser.getInstance().jsonToList(result.toString(),Mp3Info.class,"data");//GsonTools.getList(result.toString(), Mp3Info.class);
                    if (storyList != null && !storyList.isEmpty()) {
                        parseStory();
                        mAdapter.setList(storyList);
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
        setCurrMp3(0);
        playFragment = new VoicePlayFragment(storyList, 0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.footer_layout, playFragment)
                .show(playFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if(position != 0){
//            if(currMp3Info!=null){
//                currMp3Info.setIsPlay(false);
//            }
//            currMp3Info = storyList.get(position-1);
//            currMp3Info.setIsPlay(true);
//            mAdapter.notifyDataSetChanged();
//            setCurrMp3();
////            Intent intent = new Intent(this,VoicePlayerActivity.class);
////            intent.putExtra("mp3Infos",storyList);
////            intent.putExtra("position",position-1);
////            startActivity(intent);
//        }
    }

    public void setCurrMp3(int position){
        mListView.setSelection(position);
        if(currMp3Info!=null){
            currMp3Info.setNotPause(false);
            currMp3Info.setIsPlay(false);
        }
        currMp3Info = storyList.get(position);
        currMp3Info.setIsPlay(true);
        currMp3Info.setNotPause(true);
        mAdapter.notifyDataSetChanged();
        tvStoryName.setText(currMp3Info.getMp3_name());
        tv_time.setText(currMp3Info.getCreate_time());
    }

    public void updateItem(boolean notPause){
        currMp3Info.setNotPause(notPause);
        mAdapter.notifyDataSetChanged();
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