package com.yikang.health.ui.me;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseActivity;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }
}
