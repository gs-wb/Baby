package com.yikang.health.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.adapter.MainAdapter;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.model.Mp3Info;
import com.yikang.health.net.retrofit.ComApi;
import com.yikang.health.server.GsonTools;
import com.yikang.health.server.JsonParser;
import com.yikang.health.ui.BaseFragment;
import com.yikang.health.ui.baby.BabyLoreDetailActivity;
import com.yikang.health.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;
import rx.functions.Func1;

public class BabyFragment extends BaseFragment implements OnClickListener, AdapterView.OnItemClickListener, TaskExpandListener {
    private SlideMenu slideMenu;
    // 4月1日,怀孕44周，0天后出生
//    private TextView tvPregnantMsg;
    private ListView lvTodayRead;
    private MainAdapter mainAdapter;
    private List<BabyLoreModel> babyLoreList = new ArrayList<BabyLoreModel>();
    public BabyFragment() {
        super();
        layoutResID = R.layout.fragment_main_baby_layout;
    }

    @SuppressLint("ValidFragment")
    public BabyFragment(SlideMenu slideMenu) {
        layoutResID = R.layout.fragment_main_baby_layout;
        this.slideMenu = slideMenu;
    }

    @Override
    protected void mSetTitleText(TextView mTitle) {
        super.mSetTitleText(mTitle);
        mTitle.setText("宝贝");
    }

    @Override
    public void initControl(View v) {
        // 初始化组件
        initComponent(v);
        laodData();
    }

    @Override
    public void initObserver() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * 初始化组件
     */
    private void initComponent(View v) {
        ImageView menuImg = (ImageView) v.findViewById(R.id.btn_top_left);
        menuImg.setImageResource(R.drawable.nemu);
        menuImg.setOnClickListener(this);
//        tvPregnantMsg = (TextView) v.findViewById(R.id.tv_pregnant_msg);
        lvTodayRead = (ListView) v.findViewById(R.id.lv_today_read);
        mainAdapter = new MainAdapter(getActivity());
        lvTodayRead.setAdapter(mainAdapter);
        lvTodayRead.setOnItemClickListener(this);
//        LayoutInflater lif = (LayoutInflater) getActivity().getSystemService(
//                Context.LAYOUT_INFLATER_SERVICE);
//        View headerView = lif.inflate(R.layout.baby_list_head_layout, null);
//        lvTodayRead.addHeaderView(headerView);

    }

    private void laodData() {
//		6 --- 孕婴手册
        YIKApplication.client.getBabyLoresByGet(getActivity(), "6", this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BabyLoreDetailActivity.class);
        intent.putExtra("babyLore", babyLoreList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_top_left) {
            if(slideMenu!=null)
            if (slideMenu.isMainScreenShowing()) {
                slideMenu.openMenu();
            } else {
                slideMenu.closeMenu();
            }
        }
    }

    @Override
    public <T> void onTaskCompleted(String resultCode, T result, int connId) {
        switch (connId) {
            case Constants.GET_BABYLORE_DATA:
                babyLoreList = (ArrayList) JsonParser.getInstance().jsonToList(result.toString(), BabyLoreModel.class, "tngou");
                if (babyLoreList != null) mainAdapter.setData(babyLoreList);
                break;
        }
    }

    @Override
    public void onTaskCanceled() {

    }

    @Override
    public void onTaskError(String resultCode, int conId, String msg) {

    }

}
