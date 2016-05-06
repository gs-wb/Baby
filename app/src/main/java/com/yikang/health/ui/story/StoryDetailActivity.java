package com.yikang.health.ui.story;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.ui.login.ChoseBabyActivity;

/**
 * Created by admin on 2016/5/6.
 */
public class StoryDetailActivity extends BaseActivity implements View.OnClickListener{
    public StoryDetailActivity(){
        super();
        layoutResID = R.layout.activity_story_detail_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText(title);
    }
    String title = "";
    @Override
    public void initIntent() {
        title = getIntent().getStringExtra("title");
    }

    @Override
    public void initControl() {
        super.initControl();
    }


    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }
}
