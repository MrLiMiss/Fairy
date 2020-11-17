package com.tengfei.fairy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.UUID;

/**
 * StringUtil
 *
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-04-15   14:00
 * @ Version :
 */
public class AndroidUtils {


    /**
     * 检查手机是否有sim卡
     */
    private static boolean hasSim(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm.getSimOperator();
        if (TextUtils.isEmpty(operator)) {
            return false;
        }
        return true;
    }

    /**
     * 获取设备蜂窝网络运营商
     *
     * @return ["中国电信CTCC":3]["中国联通CUCC:2]["中国移动CMCC":1]["other":0]["无sim卡":-1]["数据流量未打开":-2]
     */
    public static String getCellularOperatorType(Context context) {
        int opeType = -1;
        String teleType = "";
        // No sim
        if (!hasSim(context)) {
            teleType = "null";
            return teleType;
        }
        // Mobile data disabled
        if (!isMobileDataEnabled(context)) {
            opeType = -2;
            teleType = "数据流量未打卡";
//            return teleType;
        }
        // Check cellular operator
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm.getSimOperator();
        // 中国联通
        if ("46001".equals(operator) || "46006".equals(operator) || "46009".equals(operator)) {
            opeType = 2;
            teleType = "中国联通";
            // 中国移动
        } else if ("46000".equals(operator) || "46002".equals(operator) || "46004".equals(operator) || "46007".equals(operator)) {
            opeType = 1;
            teleType = "中国电信";
            // 中国电信
        } else if ("46003".equals(operator) || "46005".equals(operator) || "46011".equals(operator)) {
            opeType = 3;
            teleType = "中国移动";
        } else {
            opeType = 0;
            teleType = "null";
        }
        return teleType;
    }


    /**
     * 获取设备拨号运营商
     *
     * @return ["中国电信CTCC":3]["中国联通CUCC:2]["中国移动CMCC":1]["other":0]["无sim卡":-1]
     */
    public static int getSubscriptionOperatorType(Context context) {
        int opeType = -1;
        // No sim
        if (!hasSim(context)) {
            return opeType;
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm.getNetworkOperator();
        // 中国联通
        if ("46001".equals(operator) || "46006".equals(operator) || "46009".equals(operator)) {
            opeType = 2;
            // 中国移动
        } else if ("46000".equals(operator) || "46002".equals(operator) || "46004".equals(operator) || "46007".equals(operator)) {
            opeType = 1;
            // 中国电信
        } else if ("46003".equals(operator) || "46005".equals(operator) || "46011".equals(operator)) {
            opeType = 3;
        } else {
            opeType = 0;
        }
        return opeType;
    }


    /**
     * 判断数据流量开关是否打开
     *
     * @param context
     * @return
     */
    public static boolean isMobileDataEnabled(Context context) {
        try {
            Method method = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return (Boolean) method.invoke(connectivityManager);
        } catch (Throwable t) {
            Log.d("isMobileDataEnabled", "Check mobile data encountered exception");
            return false;
        }
    }


    /**
     * 设备唯一号存储
     */
    public static final String MOBILE_SETTING = "ZJRC_MOBILE_SETTING";

    /**
     * 设备唯一表示
     */
    public static final String MOBILE_UUID = "ZJRC_MOBILE_UUID";

//    /**
//     * imei可取则取iemi，androidID可取则添加android
//     */
//    public static String getDeviceId(Context context) {
//        String TrustUDID = null;
//        int permissionGranted = ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE");
//        StringBuilder deviceId = new StringBuilder();
//        // 渠道标志 a
//        deviceId.append("a");
//        try {
//            deviceId.append("[");
//
//            // IMEI（imei）
//            TelephonyManager tm = (TelephonyManager) context
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//            String imei = tm.getDeviceId();
//            if (StringUtil.isNotEmpty(imei)) {
//                deviceId.append("imei:");
//                deviceId.append(imei);
//                deviceId.append("|");
//                Log.d("getDeviceId : ", deviceId.toString());
//            }
//
//            /**
//             * 判断是否有拼接到 wifi | imei | sn 如果长度小于3 代表没有， 则 生成随机码
//             */
//            if (StringUtil.isEmpty(deviceId.toString()) || deviceId.toString().length() < 3) {
//                // 如果上面都没有， 则生成一个id：随机码
//                String uuid = getUUID(context);
//                if (StringUtil.isNotEmpty(uuid)) {
//                    deviceId.append("id:");
//                    deviceId.append(uuid);
//                    Logs.d("getDeviceId : ", deviceId.toString());
//                }
//            }
//        } catch (Exception e) {
//            Logs.d("TAG", e.toString());
//            deviceId.append("id:").append(getUUID(context));
//        }
//
//        deviceId.append("]");
//
//
//        return deviceId.toString();
//
//    }


    public static String getTrustUDID(Context context) {
        String TrustUDID = null;
        int permissionGranted = ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE");
        if (permissionGranted == -1) {
            Logs.e("Hrbbdata:","need permission.READ_PHONE_STATE!");
            return "need_permission.READ_PHONE_STATE";
        } else {
            TrustUDID=getDeviceId(context);

            return TrustUDID;
        }
    }

    public static String getDeviceId(Context context) {
        try {
            StringBuilder sbDeviceId = new StringBuilder();
//            String imei = getIMEI(context);
            String androidid = getAndroidId(context);
//            String serial = getSERIAL();
//            String uuid = getDeviceUUID().replace("-", "");
            String myId=getmyId();
//            if (imei != null && imei.length() > 0) {
//                sbDeviceId.append(imei);
//                sbDeviceId.append("|");
//            }

            if (androidid != null && androidid.length() > 0) {
                sbDeviceId.append(androidid);
                sbDeviceId.append("|");
            }

            if (myId != null && myId.length() > 0) {
                sbDeviceId.append(myId);
                sbDeviceId.append("|");
            }

//            if (serial != null && serial.length() > 0) {
//                sbDeviceId.append(serial);
//                sbDeviceId.append("|");
//            }

//            if (uuid != null && uuid.length() > 0) {
//                sbDeviceId.append(uuid);
//            }

//            if (sbDeviceId.length() > 0) {
//                try {
//                    byte[] hash = getHashByString(sbDeviceId.toString());
//                    String sha1 = bytesToHex(hash);
//                    if (sha1 != null && sha1.length() > 0) {
//                        return sha1;
//                    }
//                } catch (Exception var8) {
//                    var8.printStackTrace();
//                }
//            }

            if (sbDeviceId.length() > 0) {
               return sbDeviceId.toString();
            }

            return UUID.randomUUID().toString().replace("-", "");
        } catch (Exception var9) {
            var9.printStackTrace();
            Logs.e("Hrbbdata","getDeviceId-Exception:"+var9.toString());
            return "error-getDeviceId-Exception";
        }
    }

    //自定义ID(设备信息)
    public static String getmyId() {
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 +//主板
                Build.BRAND.length() % 10 +//系统定制商
                Build.CPU_ABI.length() % 10 +// cpu指令集
                Build.DEVICE.length() % 10 +//设备参数
                Build.DISPLAY.length() % 10 +//显示屏参数
                Build.HOST.length() % 10 +//
                Build.ID.length() % 10 +//修订版本列表
                Build.MANUFACTURER.length() % 10 +//硬件制造商
                Build.MODEL.length() % 10 +//版本即最终用户可见的名称,机型
                Build.PRODUCT.length() % 10 +//整个产品的名称
                Build.TAGS.length() % 10 +//描述build的标签,如未签名，debug等等
                Build.TYPE.length() % 10 +//build的类型
                Build.USER.length() % 10; //系统用户名
        // 13 digits
        return m_szDevIDShort;
    }

    private static String getDeviceUUID() {
        try {
            String dev = "3883756" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.DEVICE.length() % 10 + Build.HARDWARE.length() % 10 + Build.ID.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.SERIAL.length() % 10;
            return (new UUID((long)dev.hashCode(), (long) Build.SERIAL.hashCode())).toString();
        } catch (Exception var1) {
            var1.printStackTrace();
            Logs.e("Hrbbdata","getDeviceId-Exception:"+var1.toString());
            return "<ERR=-102>";
        }
    }

    private static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception var2) {
            Logs.e("Hrbbdata:","getIMEI-exception:"+var2.toString());
            var2.printStackTrace();
            return "<ERR=-102>";
        }
    }

    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception var2) {
            var2.printStackTrace();
            Logs.e("Hrbbdata:","getAndroidId-exception:"+var2.toString());
            return "<ERR=-102>";
        }
    }

    private static String getSERIAL() {
        try {
            return Build.SERIAL;
        } catch (Exception var1) {
            var1.printStackTrace();
            Logs.e("Hrbbdata:","getSERIAL-exception:"+var1.toString());
            return "<ERR=-102>";
        }
    }
    private static byte[] getHashByString(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.reset();
            messageDigest.update(data.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (Exception var2) {
            return "".getBytes();
        }
    }

    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();

        for(int n = 0; n < data.length; ++n) {
            String stmp = Integer.toHexString(data[n] & 255);
            if (stmp.length() == 1) {
                sb.append("0");
            }

            sb.append(stmp);
        }

        return sb.toString().toUpperCase(Locale.CHINA);
    }


    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        SharedPreferences mShare = context.getSharedPreferences(MOBILE_SETTING,
                0);
        String uuid = "";
        if (mShare != null
                && StringUtil.isNotEmpty(mShare.getString(MOBILE_UUID, ""))) {
            uuid = mShare.getString(MOBILE_UUID, "");
        }
        if (StringUtil.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            mShare.edit().putString(MOBILE_UUID, uuid).commit();
        }
        Logs.d("getUUID", "getUUID : " + uuid);
        return uuid;
    }

    public static String getClientDeviceInfo(Context ctx) {
        String deviceID = "";
        String serial = "";
        deviceID = getDeviceId(ctx);
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            Log.e("TAG", "get the system sn ERROR!", e);
        }
        Log.d("serial", "deviceID:" + deviceID);
        String buildVersion = android.os.Build.VERSION.RELEASE;
        return deviceID + "|android" + "|android|" + buildVersion + "|android";
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*
     * 取得操作系统版本号
     */
    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 取得设备的分辨率,获得手机的宽度和高度像素单位为px
     *
     * @return r[0]:宽 ,r[1]:长
     */
    public static int[] getDeviceDistinguishability(Activity ctx) {

        int[] distinguishability = null;
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        distinguishability = new int[]{dm.widthPixels, dm.heightPixels};
        return distinguishability;
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight - contentHeight;
    }

    /**
     * 标题栏高度
     *
     * @return
     */
    public static int getTitleHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {

            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            Logs.e("tag", "resourceId = " + resourceId);
            if (resourceId > 0) {
                statusHeight = context.getResources().getDimensionPixelSize(resourceId);
                Logs.e("tag", "statusHeight = " + statusHeight);
            } else {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                int height = Integer.parseInt(clazz.getField("status_bar_height")
                        .get(object).toString());
                statusHeight = context.getResources().getDimensionPixelSize(height);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static boolean isMIUI() {
        String manufacturer = Build.MANUFACTURER;
        //这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isNotchScreen() {
        return "1".equals(SystemProperties.getInstance().get("ro.miui.notch"));
    }

    /**
     * 适配notch屏幕
     *
     * @param activity
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void notchScreen(Activity activity) {
        if (isNotchScreen() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先取消全屏
            activity.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //再设置全屏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                View decorView = activity.getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().setStatusBarColor(0x00000000);
            }
        }

    }

    /**
     * 判断是否存在虚拟按键
     *
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        boolean hasNavigationBar = false;
        Resources rs = activity.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    public static void setStatusColor(Activity activity, boolean isGray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (isGray) {
                    //灰色
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    //白色
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

}
