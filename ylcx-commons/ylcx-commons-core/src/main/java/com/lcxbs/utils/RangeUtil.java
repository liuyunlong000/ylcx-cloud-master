package com.lcxbs.utils;

/**
 * @author LI NING
 * @version 1.0
 * @ClassName:DistanceUtil
 * @Description: 经纬度距离工具类
 * @date 2018/11/29
 */
public class RangeUtil {

    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);

        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);

        double c = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));

        // 6378.137赤道半径
        c = c * 6378.137;

        c = Math.round(c * 10000d) / 10000d;
        //单位：米
        return c * 1000;

    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
