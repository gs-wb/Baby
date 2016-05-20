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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryFragment extends BaseFragment implements View.OnClickListener,TaskExpandListener {

    private TextView story1,story2,story3;
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
        story1 = (TextView) v.findViewById(R.id.story_1);
        story2 = (TextView) v.findViewById(R.id.story_2);
        story3 = (TextView) v.findViewById(R.id.story_3);
        ll_grid = (LinearLayout) v.findViewById(R.id.ll_grid);
        story1.setOnClickListener(this);
        story2.setOnClickListener(this);
        story3.setOnClickListener(this);
        loadData();
    }

    @Override
    public void initObserver() {

    }

    private void loadData() {
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(getActivity(), StoryDetailActivity.class);
        switch (v.getId()){
            case R.id.story_1:
                intent.putExtra("book_id","1");
                intent.putExtra("title", "宝宝睡前故事集");
                break;
            case R.id.story_2:
                intent.putExtra("book_id","2");
                intent.putExtra("title", "成语词典");
                break;
            case R.id.story_3:
                intent.putExtra("book_id","3");
                intent.putExtra("title", "安徒生童话");
                break;
        }
        startActivity(intent);
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
    }

    @Override
    public void onTaskError(String resultCode, int conId, String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }


    @Override
    public void onTaskCanceled() {

    }
}
