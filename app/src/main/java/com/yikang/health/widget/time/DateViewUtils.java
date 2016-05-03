package com.yikang.health.widget.time;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;

import java.util.Calendar;

/**
 * Created by zwb on 2016/5/1.
 */
public class DateViewUtils {
    Context mContext;
    public DateViewUtils(Context context){
        mContext = context;
    }

    public View initFullDateView(final TextView dateView) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View timepickerview = inflater.inflate(R.layout.timepicker, null);
        ScreenInfo screenInfo = new ScreenInfo((Activity)mContext);
        final  WheelMain wheelMain = new WheelMain(timepickerview, false);
        wheelMain.screenheight = screenInfo.getHeight();
        wheelMain.screenwidth = screenInfo.getWidth();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        wheelMain.initDateTimePicker(year, month, day);
        wheelMain.setScollerListener(new WheelMain.ScollerListener(){
            @Override
            public void onWheelScoller() {
                dateView.setText(wheelMain.getTime());
            }
        });
        return timepickerview;
    }
    public View initDateDayView(int day,final TextView dateView) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View timepickerview = inflater.inflate(R.layout.timepicker_of_day, null);
        ScreenInfo screenInfo = new ScreenInfo((Activity)mContext);
        final WheelMain wheelMain = new WheelMain(timepickerview, false);
        wheelMain.screenheight = screenInfo.getHeight();
        wheelMain.screenwidth = screenInfo.getWidth();
        wheelMain.initDateTimePicker(day);
        wheelMain.setScollerListener(new WheelMain.ScollerListener() {
            @Override
            public void onWheelScoller() {
                dateView.setText(wheelMain.getTime());
            }
        });
        return timepickerview;
    }
}
