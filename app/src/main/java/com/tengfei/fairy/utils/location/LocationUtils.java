package com.tengfei.fairy.utils.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.tengfei.fairy.utils.Logs;

import java.text.DecimalFormat;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * @ Description :获取经纬度utils
 * @ Author 李腾飞
 * @ Time 2021/2/1   13:58
 * @ Version :
 */
public class LocationUtils {
    private static LocationManager locationManager;
    private static String locationProvider;
    private static String stroLcation;//位置信息


    /**
     * 获取北斗卫星定位经纬度
     * @param context
     * @return
     * todo：未能完成区分北斗
     */
    public static String getBeiDouLocation(Context context){
        if (context!=null){
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        }else {
            return "error";
        }
        //判断gps是否正常运行
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return "GPS_PROVIDER Not available";
        }
        //添加卫星状态改变监听
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=     PackageManager.PERMISSION_GRANTED) {
            return "no ACCESS_FINE_LOCATION  permission";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locationManager.registerGnssStatusCallback(new LocaCallback());
        }
        //1000位最小的时间间隔，1为最小位移变化；也就是说每隔1000ms会回调一次位置信息
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                stroLcation ="经度："+location.getLongitude()+"，纬度："+location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
        return stroLcation;
    }


    /**
     * 获取系统经纬度
     * @param context
     * @return
     */
    public static String getLocation(Context context) {
        if (context != null) {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        } else {
            Logs.e("TouchData-getLocation", "location error");
            return null;
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
            Log.e("TouchData：", "没有可用定位器");
            return null;
        }
        //获取Location
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PackageManager.PERMISSION_GRANTED != context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.e("TouchData：", "TouchData- getlocation less permissinon");
            return null;
        } else {
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                //不为空,显示地理位置经纬度
                Log.d("TouchData", "经度" + location.getLatitude() + "纬度" + location.getLongitude());

                //监视地理位置变化
                locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);
                DecimalFormat formatFour = new DecimalFormat("0.00000");
                locationStr = formatFour.format(location.getLongitude()) + "," + formatFour.format(location.getLatitude());
            } else {
                location = getLastKnownLocation(context);
                if (location != null) {
                    locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);
                    DecimalFormat formatFour = new DecimalFormat("0.00000");
                    locationStr = formatFour.format(location.getLongitude()) + "," + formatFour.format(location.getLatitude());
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
    public static Location getLastKnownLocation(Context context) {
        Location bestLocation = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PackageManager.PERMISSION_GRANTED != context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.e("TouchData：", "getLastKnownLocation less permission");
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
            Log.i("TouchData", "经度" + location.getLongitude() + "纬度" + location.getLatitude());

        }
    };
}
