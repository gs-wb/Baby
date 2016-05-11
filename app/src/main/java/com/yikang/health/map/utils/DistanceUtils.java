package com.yikang.health.map.utils;

import java.math.BigDecimal;

/**
 * Created by zwb on 2016/2/4.
 */
public class DistanceUtils {
    /**
     * 获取两个经纬度的距离
     *
     * @param long1
     * @param lat1
     * @param long2
     * @param lat2
     * @return 单位m
     */
    public static double getDistance(double long1, double lat1, double long2,
                                     double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        a = (lat1 - lat2) * Math.PI / 180.0;
        b = (long1 - long2) * Math.PI / 180.0;
        double distance;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        distance = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        BigDecimal bd = new BigDecimal(distance);
        //表明四舍五入，保留一直线位小数
        distance = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return distance;
    }
}
