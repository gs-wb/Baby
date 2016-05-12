package com.yikang.health.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.navisdk.adapter.BNRouteGuideManager;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.baidu.navisdk.adapter.BaiduNaviManager.NaviInitListener;
import com.yikang.health.map.domain.OutMapPoint;
import com.yikang.health.map.utils.DistanceUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class IdrOutNaviManager {
    private static final String APP_FOLDER_NAME = "IdrMap";
    private String mSDCardPath = null;

    public static IdrOutNaviManager mInstance;

    public static IdrOutNaviManager getInstance() {
        if (mInstance == null) {
            synchronized (IdrOutNaviManager.class) {
                if (mInstance == null) {
                    mInstance = new IdrOutNaviManager();
                }
            }
        }
        return mInstance;
    }

    public IdrOutNaviManager() {
    }

    /**
     * 启动的时候调用一次 初始化
     * @param activity
     */
    public void init(Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<Activity>(activity);
        if (initDirs() && activity != null) {
            initNavi(weakReference.get());
        }
    }

    private void initNavi(Activity activity) {
        BaiduNaviManager.getInstance().init(activity, mSDCardPath,
                APP_FOLDER_NAME, new NaviInitListener() {
                    @Override
                    public void onAuthResult(int status, String msg) {
                        Log.e("MapSDK", "NaviInitListener onAuthResult:" + msg);
                    }

                    public void initSuccess() {
                        Log.e("MapSDK","NaviInitListener initSuccess");
                    }

                    public void initFailed() {
                        Log.e("MapSDK","NaviInitListener initFailed");
                    }

                    @Override
                    public void initStart() {
                    }
                }, null,ttsHandler,ttsPlayStateListener);
    }
    /**
     * 内部TTS播报状态回传handler
     */
    private Handler ttsHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.what;
            switch (type) {
                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
                    Log.e("MapSDK","ttsHandler == TTS play start");
                    break;
                }
                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
                    Log.e("MapSDK","ttsHandler == TTS play end");
                    break;
                }
                default :
                    break;
            }
        }
    };
    /**
     * 内部TTS播报状态回调接口
     */
    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {
        @Override
        public void playEnd() {
            Log.e("MapSDK","ttsPlayStateListener == TTS play end");
        }
        @Override
        public void playStart() {
            Log.e("MapSDK","ttsPlayStateListener == TTS play start");
        }
    };

    /**
     * 发起算路
     *
     * @param s                       起点坐标
     * @param e                       终点坐标
     * @param routePlanToNaviCallBack
     */
    public void routeplanToNavi(Activity activity,OutMapPoint s, OutMapPoint e, IRoutePlanToNaviCallBack routePlanToNaviCallBack) {
        ToNavi(activity,s, e, routePlanToNaviCallBack);
    }

    private void ToNavi(Activity activity,OutMapPoint s, OutMapPoint e, IRoutePlanToNaviCallBack routePlanToNaviCallBack) {
		double d = DistanceUtils.getDistance(s.longitude, s.latitude, e.longitude, e.latitude);
		if(d < 200){
			if(routePlanToNaviCallBack!=null)routePlanToNaviCallBack.onNaviFailed("距离太近无法导航");
			return;
		}
        BNRoutePlanNode sNode = new BNRoutePlanNode(s.longitude, s.latitude,
                s.name, null, CoordinateType.BD09LL);
        BNRoutePlanNode eNode = new BNRoutePlanNode(e.longitude, e.latitude,
                e.name, null, CoordinateType.BD09LL);
        MyPlanListener planListener = new MyPlanListener(activity,sNode,routePlanToNaviCallBack);
        if(!BaiduNaviManager.isNaviInited()){
            Log.e("MapSDK","BaiduNaviManager.getInstance().init() failed");
            planListener.onRoutePlanFailed();
            return;
        }
        List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
        list.add(sNode);
        list.add(eNode);
        BaiduNaviManager.getInstance().launchNavigator(activity, list, 1,
                true, planListener);
    }

    private class MyPlanListener implements com.baidu.navisdk.adapter.BaiduNaviManager.RoutePlanListener{
        Activity activity;
        BNRoutePlanNode sNode;
        IRoutePlanToNaviCallBack routePlanToNaviCallBack;
        public MyPlanListener(Activity activity,BNRoutePlanNode sNode,IRoutePlanToNaviCallBack routePlanToNaviCallBack){
            this.activity = activity;
            this.sNode = sNode;
            this.routePlanToNaviCallBack = routePlanToNaviCallBack;
        }

        @Override
        public void onJumpToNavigator() {
            Intent intent = new Intent(activity, IdrOutGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("routePlanNode", sNode);
            intent.putExtras(bundle);
            if(routePlanToNaviCallBack!=null)routePlanToNaviCallBack.onNaviSuccess(intent);
        }

        @Override
        public void onRoutePlanFailed() {
            if(routePlanToNaviCallBack!=null)routePlanToNaviCallBack.onNaviFailed("进入导航失败");
        }
    }
    /**
     * 导航中重新设置终点并直接算路导航
     *
     * @param e
     */
    public void resetEndNodeInNavi(OutMapPoint e) {
        BNRoutePlanNode endNode = new BNRoutePlanNode(e.longitude, e.latitude,
                e.name, null, CoordinateType.BD09LL);
        BNRouteGuideManager.getInstance().resetEndNodeInNavi(endNode);
    }

    /**
     * 强制退出导航
     */
    public void forceQuitNaviWithoutDialog() {
        BNRouteGuideManager.getInstance().forceQuitNaviWithoutDialog();
    }

    /**
     * 定位成功回调接口
     */
    public interface IRoutePlanToNaviCallBack {
        void onNaviSuccess(Intent intent);

        void onNaviFailed(String msg);
    }

    private boolean initDirs() {
        mSDCardPath = getSdcardDir();
        if (mSDCardPath == null) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    /**
     * 退出应用的时候调用
     */
    public void unInit() {
        BaiduNaviManager.getInstance().uninit();
    }
}
