package com.yikang.health.ui.me;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zwb on 2016/5/2.
 */
public class PhotoManageActivity extends BaseActivity implements View.OnClickListener{
    public PhotoManageActivity(){
        super();
        layoutResID = R.layout.activity_photo_manage_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("宝宝照片");
    }

    @Override
    public void initIntent() {

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
    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }
}
