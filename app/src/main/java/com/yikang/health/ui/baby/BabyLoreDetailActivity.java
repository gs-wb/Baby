package com.yikang.health.ui.baby;

import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.ui.BaseActivity;

/**
 * Created by zwb on 2016/5/16.
 */
public class BabyLoreDetailActivity extends BaseActivity implements TaskExpandListener {
    public BabyLoreDetailActivity(){
        super();
        layoutResID = R.layout.activity_baby_lore_detail_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("孕婴手册");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {

    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {

    }
    @Override
    public void onTaskError(String resultCode, int conId, String msg) {

    }
    @Override
    public void onTaskCanceled() {

    }
    @Override
    public void saveData() {

    }

    @Override
    public void backBtnFunction() {

    }

}
