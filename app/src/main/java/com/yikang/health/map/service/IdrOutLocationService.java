package com.yikang.health.map.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.indoorun.mapapi.domain.OutMapPoint;
import com.indoorun.outapi.IdrOutLocateManager;

/**
 * Created by zhangwb on 2016/2/18.
 */
public class IdrOutLocationService extends Service {
    private IdrOutLocateManager mLocateManager;

    public class LocationBinder extends Binder {
        public IdrOutLocationService getService() {
            return IdrOutLocationService.this;
        }
    }

    private final IBinder mBinder = new LocationBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocateManager = new IdrOutLocateManager(this);
        mLocateManager.initLocation(new IdrOutLocateManager.ILocationCallBack() {
            @Override
            public void onReceiveLocation(OutMapPoint point) {
                if (mCallBack != null) mCallBack.onReceiveLocation(point);
            }

            @Override
            public void onReceiveFailed(String msg) {
                if (mCallBack != null) mCallBack.onReceiveFailed(msg);
            }
        }, 1000);
        mLocateManager.startLocate();
    }

    IdrOutLocateManager.ILocationCallBack mCallBack;

    public void registerCallback(IdrOutLocateManager.ILocationCallBack cb) {
        mCallBack = cb;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallBack = null;
        mLocateManager.onDestory();
    }

}
