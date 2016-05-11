package com.yikang.health.map;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by zhangwb on 2015/12/25.
 */
public class IdrOutApplication {
    //室外地图缩小常量
    public static final int OUTMAP_ZOOM_IN = 1;
    //室外地图放大常量
    public static final int OUTMAP_ZOOM_OUT = 2;
    //导航到达目的地广播 Action
    public static final String OUTGUIDE_ARRIVE_DEST_ACTION = "com.indoorun.mapapi.action.arrive_dest";

    public static void initialize(Context context) {
        SDKInitializer.initialize(context);
    }

}
