package com.yikang.health.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.ui.BaseFragment;
import com.yikang.health.ui.login.LoginActivity;
import com.yikang.health.ui.me.MyInfoSetintActivity;
import com.yikang.health.ui.me.OptionActivity;
import com.yikang.health.ui.me.PhotoManageActivity;

public class MeFragment extends BaseFragment implements View.OnClickListener {

    View llMe,llBabyInfo,llPic,llOpinion,llAbout,llSet;
    TextView tvCollect, tvPoints, tvMessage;

    public MeFragment() {
        super();
        layoutResID = R.layout.fragment_main_me_layout;
    }

    @Override
    public void initControl(View v) {
        llMe = v.findViewById(R.id.ll_me);
        tvCollect = (TextView) v.findViewById(R.id.tv_collect);
        tvPoints = (TextView) v.findViewById(R.id.tv_points);
        tvMessage = (TextView) v.findViewById(R.id.tv_message);

        llBabyInfo = v.findViewById(R.id.ll_baby_info);
        llPic = v.findViewById(R.id.ll_pic);
        llOpinion = v.findViewById(R.id.ll_opinion);
        llAbout = v.findViewById(R.id.ll_about);
        llSet = v.findViewById(R.id.ll_set);

    }


    @Override
    public void initObserver() {
        llMe.setOnClickListener(this);
        tvCollect.setOnClickListener(this);
        tvPoints.setOnClickListener(this);
        tvMessage.setOnClickListener(this);

        llBabyInfo.setOnClickListener(this);
        llPic.setOnClickListener(this);
        llOpinion.setOnClickListener(this);
        llAbout.setOnClickListener(this);
        llSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_me:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_collect:

                break;
            case R.id.tv_points:
                startActivity(new Intent(getActivity(), MyInfoSetintActivity.class));
                break;
            case R.id.tv_message:

                break;
            case R.id.ll_baby_info:

                break;
            case R.id.ll_pic:
                startActivity(new Intent(getActivity(), PhotoManageActivity.class));
                break;
            case R.id.ll_opinion:
                startActivity(new Intent(getActivity(), OptionActivity.class));
                break;
            case R.id.ll_about:

                break;
            case R.id.ll_set:

                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

}
