package com.yikang.health.ui.baby;

import android.webkit.WebView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.BabyLoreDetail;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.server.JsonParser;
import com.yikang.health.ui.BaseActivity;

import java.util.ArrayList;

/**
 * Created by zwb on 2016/5/16.
 */
public class BabyLoreDetailActivity extends BaseActivity implements TaskExpandListener {
    private TextView tvTitle, tvTime, tvDes, tvKey;
    private WebView webView;
    private BabyLoreModel babyLore;

    public BabyLoreDetailActivity() {
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
        babyLore = getIntent().getParcelableExtra("babyLore");
    }

    @Override
    public void initControl() {
        super.initControl();
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDes = (TextView) findViewById(R.id.tv_des);
        tvKey = (TextView) findViewById(R.id.tv_key);
        webView = (WebView) findViewById(R.id.webView);
        if (babyLore != null)
            YIKApplication.client.getBabyLoreDetailByGet(this, babyLore.getId()+"", this);
    }

    private void setViews(BabyLoreDetail loreDetail) {
        tvTitle.setText(loreDetail.getTitle());
//        tvTime.setText(loreDetail.getTime());
        tvDes.setText(loreDetail.getDescription());
        tvKey.setText(loreDetail.getKeywords());
        webView.loadDataWithBaseURL(null, loreDetail.getMessage(), "text/html", "UTF-8", null);
//        webView.loadData(loreDetail.getMessage(), "text/html", "utf-8");
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {

    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        switch (connId) {
            case Constants.GET_BABYLORE_DETAIL_DATA:
                BabyLoreDetail loreDetail = (BabyLoreDetail) JsonParser.getInstance().jsonToEntity(result.toString(), BabyLoreDetail.class);
                if (loreDetail != null) setViews(loreDetail);
                break;
        }
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
