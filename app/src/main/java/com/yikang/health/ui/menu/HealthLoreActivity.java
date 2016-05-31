package com.yikang.health.ui.menu;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.adapter.LBaseAdapter;
import com.yikang.health.adapter.LoreAdapter;
import com.yikang.health.adapter.WeatherIndexAdapter;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.model.WeatherModel;
import com.yikang.health.net.retrofit.ComApi;
import com.yikang.health.net.retrofit.model.HttpLoreResult;
import com.yikang.health.ui.BaseActivity;
import com.yikang.health.utils.LogUtil;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zwb on 2016/5/2.
 */
public class HealthLoreActivity extends BaseActivity {
    private ListView indexListView;

    public HealthLoreActivity() {
        super();
        layoutResID = R.layout.activity_health_knowledge_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("基础知识");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initControl() {
        super.initControl();
        indexListView = (ListView) findViewById(R.id.indexListView);
        loadData();
    }

    private void loadData() {
        add(ComApi.getInstance().getHealthLoreByGet("6")
                .doOnNext(httpLoreResult -> setView(httpLoreResult.getTngou()))
                .doOnError(Throwable::printStackTrace)
                .onErrorResumeNext(Observable.empty())
                .subscribe());
    }

    private void setView(List<BabyLoreModel> babyLoreModels) {
        LoreAdapter adapter = new LoreAdapter(this);
        indexListView.setAdapter(adapter);
        adapter.setDataSource(babyLoreModels);

    }

    @Override
    public void initVariable() {

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
