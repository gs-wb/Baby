package com.yikang.health.ui.me;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.ui.BaseActivity;

/**
 * Created by zwb on 2016/5/2.
 */
public class OptionActivity extends BaseActivity implements View.OnClickListener, TaskExpandListener {
    private Button btnSubmit;
    private EditText etOption;

    public OptionActivity() {
        super();
        layoutResID = R.layout.activity_option_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("意见反馈");
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initControl() {
        super.initControl();
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etOption = (EditText) findViewById(R.id.edit_option);
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void initVariable() {

    }

    @Override
    public void initObserver() {
    }

    @Override
    public void onClick(View v) {
        YIKApplication.client.addFeedback(this, "1462352629699783134", "意见", "有意见啦", this);
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        if(resultCode.equals(Constants.RESULT_SUCCESS)){
            if(connId == Constants.ADD_FEED_BACK){
                ToastShow(result.toString());
            }
        }else{
            ToastShow(result.toString());
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
