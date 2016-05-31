package com.yikang.health.ui.menu;

import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.adapter.LoreAdapter;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.net.retrofit.ComApi;
import com.yikang.health.ui.BaseActivity;

import java.util.List;

import rx.Observable;

/**
 * Created by zwb on 2016/5/2.
 */
public class HealthNutritionActivity extends BaseActivity {
    private ListView indexListView;

    public HealthNutritionActivity() {
        super();
        layoutResID = R.layout.activity_health_knowledge_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("健康饮食");
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
        add(ComApi.getInstance().getHealthLoreByGet("3")
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
