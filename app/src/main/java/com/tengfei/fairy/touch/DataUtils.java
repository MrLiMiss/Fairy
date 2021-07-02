package com.tengfei.fairy.touch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.utils.Logs;

import java.text.DecimalFormat;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * @ Description :埋点相关utils
 * @ Author 李腾飞
 * @ Time 2021/1/8   17:43
 * @ Version :
 */
public class DataUtils {
    private static LocationManager locationManager;
    private static String locationProvider;


    /**
     * 获取设备经纬度信息格式（12.12345:124.12345）：
     * @param context
     * @return
     */
    public static String getLocation(Context context) {
        if (context != null) {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        } else {
            Logs.e("Hrbbdata-getLocation", "location error");
            return "location error";
        }
        List<String> providers = locationManager.getProviders(true);
        String locationStr = "";
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Log.e("Hrbbdata：", "没有可用定位器");
            return null;
        }
        //获取Location
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PackageManager.PERMISSION_GRANTED != context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            return "Hrbbdata-less permissinon";
        } else {
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                //不为空,显示地理位置经纬度
                Log.d("Hrbbdata", "经度" + location.getLongitude() + "纬度" + location.getLatitude());

                //监视地理位置变化
                locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);
                DecimalFormat formatFour = new DecimalFormat("0.0000");
                Constants.altitude = formatFour.format(location.getLongitude()) + "";
                Constants.latitude = formatFour.format(location.getLatitude()) + "";
                locationStr = Constants.altitude + "," + Constants.latitude;
            } else {
                location = getLastKnownLocation(context);
                if (location != null) {
                    locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);
                    DecimalFormat formatFour = new DecimalFormat("0.00000");
                    Constants.altitude = formatFour.format(location.getLongitude()) + "";
                    Constants.latitude = formatFour.format(location.getLatitude()) + "";
                    locationStr = Constants.altitude + "," + Constants.latitude;
                } else {
                    return null;
                }

            }

            locationManager.removeUpdates(locationListener);
        }

        return locationStr;

    }

    /**
     * 定位：得到位置对象
     *
     * @return
     */
    public static Location getLastKnownLocation (Context context) {
        Location bestLocation = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PackageManager.PERMISSION_GRANTED != context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.e("Hrbbdata：", "getLastKnownLocation less permission");
            return null;
        } else {
            //获取地理位置管理器
            LocationManager mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            List<String> providers = mLocationManager.getProviders(true);
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }


    static LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示 如果位置改变，经纬度没有变，不会执行此函数 应该说经纬度发生变化执行此函数
            Log.i("TAG", "经度" + location.getLongitude() + "纬度" + location.getLatitude());

        }
    };


}
