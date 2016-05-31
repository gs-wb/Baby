package com.yikang.health.ui.menu;

import android.graphics.Paint;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.WeatherIndexAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.WeatherModel;
import com.yikang.health.net.retrofit.ComApi;
import com.yikang.health.server.JsonParser;
import com.yikang.health.ui.BaseActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zwb on 2016/5/2.
 */
public class WeatherActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_date, tv_city, tv_temp, tv_temp_update, tv_temp_diff;
    private TextView tv_weather, tv_wd_ws, tv_sunrise, tv_sunset;
    private ListView indexListView;

    public WeatherActivity() {
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
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_temp = (TextView) findViewById(R.id.tv_temp);
        tv_temp_update = (TextView) findViewById(R.id.tv_temp_update);
        tv_temp_diff = (TextView) findViewById(R.id.tv_temp_diff);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_wd_ws = (TextView) findViewById(R.id.tv_wd_ws);
        tv_sunrise = (TextView) findViewById(R.id.tv_sunrise);
        tv_sunset = (TextView) findViewById(R.id.tv_sunset);
        indexListView = (ListView) findViewById(R.id.indexListView);
        loadData();
    }

    private void loadData() {
        add(ComApi.getInstance().getWeather("上海")
                .doOnNext(this::setWeatherView)
                .doOnError(Throwable::printStackTrace)
                .onErrorResumeNext(Observable.empty())
                .subscribe());
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

    private void setWeatherView(WeatherModel weatherModel) {
        if (weatherModel != null) {
            tv_date.setText(weatherModel.getToday().getDate() + " " + weatherModel.getToday().getWeek());
            tv_city.setText(weatherModel.getCity() + "【切换】");
            tv_city.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            tv_temp_diff.setText(weatherModel.getToday().getLowtemp() + "~" + weatherModel.getToday().getHightemp());
            tv_temp.setText(weatherModel.getToday().getCurTemp());

            tv_weather.setText(weatherModel.getToday().getType());
            tv_wd_ws.setText(weatherModel.getToday().getFengxiang() + " " + weatherModel.getToday().getFengli());
            WeatherIndexAdapter indexAdapter = new WeatherIndexAdapter(this);
            indexListView.setAdapter(indexAdapter);
            indexAdapter.setData(weatherModel.getToday().getIndex());
        }
    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }
}
