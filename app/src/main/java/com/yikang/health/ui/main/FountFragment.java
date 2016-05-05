package com.yikang.health.ui.main;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseFragment;
import com.yikang.health.ui.circle.CircleActivity;
import com.yikang.health.ui.fount.LookForActivity;
import com.yikang.health.ui.fount.TreasureBoxActivity;

public class FountFragment extends BaseFragment implements OnClickListener {
    View llBaby, llCoinBox, llFind;

    public FountFragment() {
        super();
        layoutResID = R.layout.fragment_main_find_layout;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("发现");
    }

    @Override
    public void initControl(View view) {
        llBaby = view.findViewById(R.id.ll_baby);
        llCoinBox = view.findViewById(R.id.ll_coin_box);
        llFind = view.findViewById(R.id.ll_find);
        llBaby.setOnClickListener(this);
        llCoinBox.setOnClickListener(this);
        llFind.setOnClickListener(this);
    }

    @Override
    public void initObserver() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_baby:
                startActivity(new Intent(getActivity(), CircleActivity.class));
                break;
            case R.id.ll_coin_box:
                startActivity(new Intent(getActivity(), TreasureBoxActivity.class));
                break;
            case R.id.ll_find:
                startActivity(new Intent(getActivity(), LookForActivity.class));
                break;
            default:
                break;
        }
    }


}
