package com.yikang.health.ui.menu;

import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseActivity;

/**
 * Created by zwb on 2016/5/21.
 */
public class CountFetalMoveActivity extends BaseActivity{
    public CountFetalMoveActivity(){
        super();
        layoutResID = R.layout.activity_count_fetal_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("数胎动");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initControl() {
        super.initControl();
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
}
