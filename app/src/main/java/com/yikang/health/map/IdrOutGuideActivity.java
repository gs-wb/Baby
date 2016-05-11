package com.yikang.health.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.baidu.navisdk.adapter.BNRouteGuideManager;
import com.baidu.navisdk.adapter.BNRouteGuideManager.CustomizedLayerItem;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.indoorun.mapapi.domain.IdrMapRegion;
import com.indoorun.mapapi.exception.NoBluetoothException;
import com.indoorun.mapapi.outdoor.IdrAndOutDector;
import com.indoorun.mapapi.outdoor.IdrAndOutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JueFang on 2015/11/30.
 * modify by zhangwb
 */
public class IdrOutGuideActivity extends Activity implements IdrAndOutListener {//,BDLocationListener {

    private BNRoutePlanNode mBNRoutePlanNode = null;
    private LocalBroadcastManager mLocalBroadcastManager;
    private IdrAndOutDector idrAndOutDector = new IdrAndOutDector(IdrOutGuideActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createHandler();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        }
        View view = BNRouteGuideManager.getInstance().onCreate(this, new BNRouteGuideManager.OnNavigationListener() {

            @Override
            public void onNaviGuideEnd() {
                finish();
            }

            @Override
            public void notifyOtherAction(int actionType, int arg1, int arg2, Object obj) {

            }
        });

        if (view != null) {
            setContentView(view);
        }
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                mBNRoutePlanNode = (BNRoutePlanNode) bundle.getSerializable("routePlanNode");
            }
        }
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(IdrOutGuideActivity.this);

        try {
            idrAndOutDector.startLocateByRegions(1000 * 30, 1000);
            idrAndOutDector.setIdrAndOutListener(this);
        } catch (NoBluetoothException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOutdoor() {

    }

    @Override
    public void onIndoor(List<IdrMapRegion> regions) {
        if(isFinishing())return;
        Intent intent = new Intent();
        intent.setAction(IdrOutApplication.OUTGUIDE_ARRIVE_DEST_ACTION);
        mLocalBroadcastManager.sendBroadcast(intent);
        BNRouteGuideManager.getInstance().forceQuitNaviWithoutDialog();
        IdrOutGuideActivity.this.finish();
    }

    @Override
    protected void onResume() {
        BNRouteGuideManager.getInstance().onResume();
        super.onResume();
        hd.sendEmptyMessageDelayed(MSG_SHOW, 5000);
    }

    protected void onPause() {
        super.onPause();
        BNRouteGuideManager.getInstance().onPause();
    }

    @Override
    protected void onDestroy() {
        BNRouteGuideManager.getInstance().onDestroy();
        idrAndOutDector.stopLocateByRegions();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        BNRouteGuideManager.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        BNRouteGuideManager.getInstance().onBackPressed(false);
    }

    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        BNRouteGuideManager.getInstance().onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    private void addCustomizedLayerItems() {
        List<CustomizedLayerItem> items = new ArrayList<CustomizedLayerItem>();
        CustomizedLayerItem item1 = null;
        if (mBNRoutePlanNode != null) {
            item1 = new CustomizedLayerItem(mBNRoutePlanNode.getLongitude(), mBNRoutePlanNode.getLatitude(),
                    mBNRoutePlanNode.getCoordinateType(), null, CustomizedLayerItem.ALIGN_CENTER);
            items.add(item1);

            BNRouteGuideManager.getInstance().setCustomizedLayerItems(items);
        }
        BNRouteGuideManager.getInstance().showCustomizedLayer(true);
    }

    private static final int MSG_SHOW = 1;
    private static final int MSG_HIDE = 2;
    private Handler hd = null;

    private void createHandler() {
        if (hd == null) {
            hd = new Handler(getMainLooper()) {
                public void handleMessage(Message msg) {
                    if (msg.what == MSG_SHOW) {
                        addCustomizedLayerItems();
//						hd.sendEmptyMessageDelayed(MSG_HIDE, 5000);
                    } else if (msg.what == MSG_HIDE) {
                        BNRouteGuideManager.getInstance().showCustomizedLayer(false);
//						hd.sendEmptyMessageDelayed(MSG_SHOW, 5000);
                    }

                }
            };
        }
    }

}
