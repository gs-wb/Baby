package com.yikang.health.ui.menu;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.WeatherModel;
import com.yikang.health.server.JsonParser;
import com.yikang.health.ui.BaseActivity;

import org.json.JSONObject;

/**
 * Created by zwb on 2016/5/2.
 */
public class WeatherActivity extends BaseActivity implements View.OnClickListener, TaskExpandListener {
    private TextView tv_date, tv_city, tv_temp, tv_temp_update, tv_temp_diff;
    private TextView tv_weather, tv_wd_ws, tv_sunrise, tv_sunset;

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
    WeatherModel weatherModel;
    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if (connId == Constants.GET_WEATHER_DATA) {
            try {
                JSONObject object = new JSONObject(result.toString());
                JSONObject retData = object.getJSONObject("retData");
                weatherModel = (WeatherModel) JsonParser.getInstance().jsonToEntity(retData.toString(), WeatherModel.class);
                setWeatherView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setWeatherView() {
        if(weatherModel!=null){
            tv_date.setText(weatherModel.getDate());
            tv_city.setText(weatherModel.getCity()+"【切换】");
            tv_city.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            tv_temp_update.setText("【"+weatherModel.getTime()+"更新】");
            tv_temp_diff.setText(String.format(getResources().getString(R.string.weather_unit), weatherModel.getL_tmp())
                    +"~"+String.format(getResources().getString(R.string.weather_unit), weatherModel.getH_tmp()));
            tv_temp.setText("【实时】"+String.format(getResources().getString(R.string.weather_unit), weatherModel.getTemp()));

            tv_weather.setText(weatherModel.getWeather());
            tv_wd_ws.setText(weatherModel.getWD()+" "+weatherModel.getWS());
            tv_sunrise.setText("日出"+weatherModel.getSunrise());
            tv_sunset.setText("日落"+weatherModel.getSunset());
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
