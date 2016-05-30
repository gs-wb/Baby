package com.yikang.health.ui.me;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.net.retrofit.ComApi;
import com.yikang.health.net.retrofit.model.HttpResult;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.ui.login.ChoseBabyActivity;

/**
 * Created by zwb on 2016/5/2.
 */
public class MyInfoSetintActivity extends BaseActivity implements View.OnClickListener{
    View ll_state,ll_reserve1,ll_reserve2;
    public MyInfoSetintActivity(){
        super();
        layoutResID = R.layout.activity_myinfo_seting_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("设置我的信息");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initControl() {
        super.initControl();
        ll_state = findViewById(R.id.ll_state);
        ll_reserve1 = findViewById(R.id.ll_reserve1);
        ll_reserve2 = findViewById(R.id.ll_reserve2);
        add(ComApi.getInstance().getMp3List(1,"1")
                .doOnNext(mp3Infos -> Log.e("data",mp3Infos.toString()))
                .doOnError(throwable -> {
                    ToastShow("获取数据失败");
                })
                .onErrorResumeNext(rx.Observable.empty())
                .subscribe());
    }


    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {
        ll_state.setOnClickListener(this);
        ll_reserve1.setOnClickListener(this);
        ll_reserve2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_state:
                startActivityForResult(new Intent(this, ChoseBabyActivity.class), 1);
                break;
            case R.id.ll_reserve1:

                break;
            case R.id.ll_reserve2:

                break;

        }
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
