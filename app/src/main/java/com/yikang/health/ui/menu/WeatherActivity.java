package com.yikang.health.ui.menu;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.ui.BaseActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zwb on 2016/5/2.
 */
public class WeatherActivity extends BaseActivity implements View.OnClickListener,TaskExpandListener {
    public WeatherActivity(){
        super();
        layoutResID = R.layout.activity_weather_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("天气");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initControl() {
        super.initControl();
        YIKApplication.client.getWeatherByGet(this, "上海", this);
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
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if(connId == Constants.GET_WEATHER_DATA){
//            ToastShow(result.toString());
        }
    }
    @Override
    public void onTaskCanceled() {

    }

    @Override
    public void onTaskError(String resultCode, int conId, String msg) {

    }
    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }
}
