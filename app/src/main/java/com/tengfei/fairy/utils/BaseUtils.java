package com.tengfei.fairy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtils {

    /**
     * 手机设备连接网络判断
     *
     * @return
     */
    public static boolean isNetWork(Activity activity) {
        boolean newWorkOK = false;
        ConnectivityManager connectManager = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectManager.getActiveNetworkInfo() != null) {
            newWorkOK = true;
        }
        return newWorkOK;
    }

    public static boolean isOpenGPS(Activity activity) {
        boolean isOpneGPS = false;
        LocationManager alm = (LocationManager) activity
                .getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // gps已开启
            isOpneGPS = true;
        } else {
            // gps未开启
            isOpneGPS = false;
        }
        return isOpneGPS;
    }

    private static long lastClickTime;
    private static long lastClickTime1;

    /**
     * 判断事件出发时间间隔是否超过预定值
     *
     * @return
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2013年7月22日
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastDoubleClick(long ms) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime1;
        if (0 < timeD && timeD < ms) {
            return true;
        }
        lastClickTime1 = time;
        return false;
    }

    //判断相机是否可用；思路：如果相机被禁用，Camera.open()会报错，根据是否报错来判断相机是否可用
    public static boolean isCamera() {
        boolean isCamera = true;
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // TODO: handle exception
            isCamera = false;
        }
        if (isCamera) {
            camera.release();
            camera = null;
        }
        return isCamera;
    }

    //获取手机mac地址
    public static String getMacAddress2(Context mContext) {
        //在wifi未开启状态下，仍然可以获取MAC地址
        String macAddress = null;
        WifiManager wifiMgr = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
        if (null != info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }

    /**
     * 获取MAC地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        String mac;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mac = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mac = getMacAddressM();
        } else {
            mac = getMacFromHardware();
        }
        return mac;
    }

    /**
     * Android  6.0 之前（不包括6.0）
     * 必须的权限  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     * @param context
     * @return
     */
    private static String getMacDefault(Context context) {
        String mac = "02:00:00:00:00:00";
        if (context == null) {
            return mac;
        }
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi == null) {
            return mac;
        }
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (info == null) {
            return mac;
        }
        mac = info.getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }

    /**
     * Android 6.0（包括） - Android 7.0（不包括）
     * @return
     */
    private static String getMacAddressM() {
        String wifiaddress = "02:00:00:00:00:00";
        try {
            wifiaddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wifiaddress;
    }

    /**
     * 遍历循环所有的网络接口，找到接口是 wlan0
     * 必须的权限 <uses-permission android:name="android.permission.INTERNET" />
     * @return
     */
    private static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    // 身份证校验
    public static boolean isCertNo(String certNoStr) {
        boolean isCartNo = false;
        // 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位只可以为X）
        Pattern idNumPattern = Pattern
                .compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}(\\d|X))");
        // 通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(certNoStr);
        // 判断用户输入是否为身份证号
        if (idNumMatcher.matches()) {
            isCartNo = true;
        }
        return isCartNo;
    }

    /*
     *  判断正则表达式匹配方法
     *  pattern：正则表达式
     *  matcher：判断是否符合pattern的对象
     */
    public static boolean isMatcher(String pattern, String matcher) {
        boolean isMatcher = false;
        // 正则表达式对象
        Pattern mPattern = Pattern.compile(pattern);
        // 通过Pattern获得Matcher
        Matcher mMatcher = mPattern.matcher(matcher);
        // 判断用户输入是否匹配
        if (mMatcher.matches()) {
            isMatcher = true;
        }
        return isMatcher;
    }

    //获取版本号
    public static String getVersionName(Activity activity) {
        String versionName = "1.0";
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            Logs.d("TAG", e.toString());
        }
        return versionName;
    }

//    /**
//     * @param activity  依赖的activity
//     * @param errorCode 005 会话超时  006 单点登录
//     * @param errorMsg  错误提示信息
//     * @param flg       true 依赖MainActivity false 其他activity
//     */
//    public static void sessionOut(final Activity activity, String errorCode, String errorMsg, final boolean flg) {
//        if ("005".equals(errorCode) || "006".equals(errorCode)) {
//            //用户被挤掉,提示信息格式要求
//            if (errorMsg.contains("lmth")) {
//                errorMsg = errorMsg.replace("lmth", "\n");
//            }
//            MyMessageTools.showOnClickDialog(null, new MyDialog.onYesOnclickListener() {
//                @Override
//                public void onYesClick() {
//                    clearUserInfo(activity,true);
//                    if (flg) {
//                        activity.finish();
//                    }
//                    GestureUtil.requestIsSetGesture(activity);
//
//                }
//            }, errorMsg, activity, "确定", "取消", "温馨提示");
//        } else {
//            ToastTools.showShort(activity, errorMsg);
//        }
//    }


//    /**
//     * 清楚cookie，用户信息，标记未登录，发送Eventbus
//     * 跳转登录页面
//     * @param activity
//     */
//    public static void clearUserInfo(Activity activity,boolean flag){
//        CookieSyncManager.createInstance(activity);
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.removeAllCookie();
//        cookieManager.removeSessionCookie();
//        UserInfoResult.getInstence().clear();
//        Constans.ISlOGIN = false;
//        Constans.ISSESSIONOUT = true;
//        EventBus.getDefault().post(new LoginEvent(false));
////							activity.startActivity(new Intent(activity, LoginActivity.class));// 在webactivity超时，返回登录界面
//        if(flag) {
//            IntentUtils.toLogin(activity, false);
//        }
//    }

    /**
     * 判断手机是否ROOT
     */
    public static boolean isRoot() {
        boolean root = false;
        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }

        } catch (Exception e) {
        }

        return root;
    }

//    // 版本更新请求
//    public static void versionCheck(final Activity activity) {
//        Logs.d("TAG", "versionCheck------->");
//        try {
//            final String version = BaseUtils.getVersionName(activity);
//            JSONObject loginJson = new JSONObject();
//            loginJson.put("M_OS", "2");
//            loginJson.put("APP_ID", "2");
//            loginJson.put("APP_VERS", version);
//            // ProgressDialogBar.loadWaitPanel(activity, null, true);
//            String key = CryptoUtil.genRadomKey(16);
//            APPRestClient.postNewClient(activity, activity.getApplication(),
//                    Constans.urlMap.get("clientVersion") + "?M_OS=2&APP_ID=2&APP_VERS=" + version, loginJson.toString(),
//                    new APPResponseHandler<String>(String.class, true, key) {
//
//                        @Override
//                        public void onSuccess(String result) {
//                            // TODO Auto-generated method stub
//                            // ProgressDialogBar.dismissProgressBar();
//                            JSONObject jsonObject;
//                            try {
//                                jsonObject = new JSONObject(result);
//                                String msg = jsonObject.getString("APP_MARK");
//                                String vers = jsonObject.getString("APP_VERS");
//                                final String url = jsonObject.getString("APP_URL");
//                                Logs.d("TAG", "url===" + url);
//                                ProgressDialogBar.dismissProgressBar();
//                                int status = jsonObject.getInt("VER_STUS");// 1:最新版本//
//                                // 2：有更新//
//                                // 3：强制更新
//                                switch (status) {
//                                    case 1:
//                                        // 无更新
//                                        Constans.ISNEWVERSION = false;
//                                        break;
//                                    case 2:
//                                        new VersionUpdateUtils(activity).showUpdateAlertDialog(false, url, msg, vers);
//                                        break;
//                                    case 3:
//                                        new VersionUpdateUtils(activity).showUpdateAlertDialog(true, url, msg, vers);
//                                        break;
//
//                                    default:
//                                        break;
//                                }
//                            } catch (JSONException e) {
//                                // TODO Auto-generated catch block
//                                Logs.d("TAG", e.toString());
//                            }
//                        }
//
//
//                        @Override
//                        public void onFailure(String errorCode,
//                                              String errorMsg) {
//                            Logs.d("TAG", "版本更新错误errorMsg--->"
//                                    + errorMsg);
//                        }
//
//                    }, key);
//        } catch (Exception e) {
//        }
//    }


}
