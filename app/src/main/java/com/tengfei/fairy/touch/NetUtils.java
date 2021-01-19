package com.tengfei.fairy.touch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.tengfei.fairy.utils.Logs;
import com.tengfei.fairy.utils.StringUtil;
import com.tengfei.fairy.utils.ToastTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Enumeration;

import static android.content.ContentValues.TAG;

/**
 * @ Description :网络相关
 * @ Author 李腾飞
 * @ Time 2020-05-08   10:15
 * @ Version :
 */

public class NetUtils {

    public static final int NETWORK_TYPE_NR = 20;
    public static final int SDK_VERSION_Q = 29;
    public final static String GET_IP = "http://www.3322.org/dyndns/getip";



    //从接口中提取IP
    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            infoUrl = new URL(GET_IP);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                JSONObject jsonObject = new JSONObject(strber.toString());
                //从反馈的结果中提取出IP地址
                return jsonObject.getString("ip");
            }
        } catch (Exception e) {
            return "127.0.0.1";
        }
        return "127.0.0.1";
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

    private static boolean hasSim(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String operator = tm.getSimOperator();
            if(TextUtils.isEmpty(operator))
            {
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
        String opType = null;
        // No sim
        if (!hasSim(context)) {
            return null;
        }
        // Mobile data disabled
        if (!isMobileDataEnabled(context)) {
            opeType = -2;
            opType="null";
            return opType;
        }
        // Check cellular operator
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm.getSimOperator();
        // 中国联通
        if ("46001".equals(operator) || "46006".equals(operator) || "46009".equals(operator)) {
            opeType = 2;
            opType="中国联通";
            // 中国移动
        } else if ("46000".equals(operator) || "46002".equals(operator) || "46004".equals(operator) || "46007".equals(operator)) {
            opeType = 1;
            opType="中国移动";
            // 中国电信
        } else if ("46003".equals(operator) || "46005".equals(operator) || "46011".equals(operator)) {
            opeType = 3;
            opType="中国电信";
        } else {
            opeType = 0;
            opType="其他";
        }
        return opType;
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



    public static String getNetworkType(Context context) {
            String strNetworkType = "null";
            NetworkInfo networkInfo = ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            WifiManager wifimanage = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);// 获取WifiManager

            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    WifiInfo wifiInfo = wifimanage.getConnectionInfo();
                    String ip = intToIp(wifiInfo.getIpAddress());
//                    strNetworkType = "WIFI:" + ip;
                    strNetworkType = "WIFI" ;//只显示网络类型

                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    String _strSubTypeName = networkInfo.getSubtypeName();

                    // TD-SCDMA networkType is 17
                    int networkType = networkInfo.getSubtype();
                    int adjustType=adjustNetworkType(context,networkType);
                    switch (adjustType) {
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN: // api<8 : replace by
                            // 11
                            strNetworkType = "2G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B: // api<9 : replace by
                            // 14
                        case TelephonyManager.NETWORK_TYPE_EHRPD: // api<11 : replace by
                            // 12
                        case TelephonyManager.NETWORK_TYPE_HSPAP: // api<13 : replace by
                            // 15
                            strNetworkType = "3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE: // api<11 : replace by
                            // 13
                            strNetworkType = "4G";
                            break;
                        case NETWORK_TYPE_NR: // api<11 : replace by
                            // 20
                            strNetworkType = "5G";
                            break;
                        default:
                            // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                            if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA")
                                    || _strSubTypeName.equalsIgnoreCase("WCDMA")
                                    || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                                strNetworkType = "3G";
                            } else if (StringUtil.isNotEmpty(_strSubTypeName)){
                                strNetworkType = _strSubTypeName;
                            }

                            break;
                    }

                    return strNetworkType;
                }
            }
            return strNetworkType;

        }

    /**
     * get the 5G network type
     *
     * @param ctx Context
     * @param networkTypeFromSys this method can be call only when networkTypeFromSys = 13(LET)
     * @return correct network type
     */
    private static int adjustNetworkType(Context ctx, int networkTypeFromSys) {
        int networkType = networkTypeFromSys;
        if (Build.VERSION.SDK_INT >= SDK_VERSION_Q
                && ctx.checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            try {
                TelephonyManager tm = (TelephonyManager) ctx
                        .getSystemService(Context.TELEPHONY_SERVICE);
                ServiceState ss;
                int defaultDataSubId = getSubId();
                if (defaultDataSubId == -1) {
                    ss = tm.getServiceState();
                } else {
                    try {
                        Class<TelephonyManager> infTm = TelephonyManager.class;
                        Method method = infTm
                                .getDeclaredMethod("getServiceStateForSubscriber",
                                        new Class[]{int.class});
                        method.setAccessible(true);
                        ss = (ServiceState) method.invoke(tm, defaultDataSubId);
                    } catch (Throwable t) {
                        ss = tm.getServiceState();
                    }
                }
                if (ss != null && isServiceStateFiveGAvailable(ss.toString())) {
                    networkType = NETWORK_TYPE_NR;
                }
            } catch (Exception e) {
                // do nothing
            }
        }
        return networkType;
    }

    /**
     * check the service state str is 5G
     *
     * @param ss services state str
     * @return true if is 5G
     */
    private static boolean isServiceStateFiveGAvailable(String ss) {
        boolean available = false;
        if (!TextUtils.isEmpty(ss)
                && (ss.contains("nrState=NOT_RESTRICTED")
                || ss.contains("nrState=CONNECTED"))) {
            available = true;
        }
        return available;
    }

    /**
     * get data sub id
     *
     * @return subId
     */
    private static int getSubId() {
        int defaultDataSubId = -1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            defaultDataSubId = SubscriptionManager.getDefaultDataSubscriptionId();
        }
        return defaultDataSubId;
    }


    /**
         * @return 是否有活动的网络连接
         */
        public static boolean hasNetWorkConnection(Context context) {
            //获取连接活动管理器
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取链接网络信息
            final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            return (networkInfo != null && networkInfo.isAvailable());

        }

        public static String intToIp(int ipInt) {
            return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
                    .append((ipInt >> 16) & 0xff).append('.')
                    .append((ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
                    .toString();
        }





    // wifi下获取本地网络IP地址（局域网地址）
    public static String getLocalIPAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            @SuppressLint("MissingPermission") WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
            return ipAddress;
        }
        return "";
    }


    public static String intIP2StringIP(int ipInt) {
        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
                .append((ipInt >> 16) & 0xff).append('.')
                .append((ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
                .toString();
    }
    // 获取有限网IP
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {

        }
        return "0.0.0.0";

    }

    /**
     * 获取外网ip地址（非本地局域网地址）的方法
     */
    public static String getOutNetIP() {
        String ipAddress = "";
        try {
            String address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
            URL url = new URL(address);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36"); //设置浏览器ua 保证不出现503

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                // 将流转化为字符串
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));

                String tmpString;
                StringBuilder retJSON = new StringBuilder();
                while ((tmpString = reader.readLine()) != null) {
                    retJSON.append(tmpString + "\n");
                }

                JSONObject jsonObject = new JSONObject(retJSON.toString());
                String code = jsonObject.getString("code");

                Log.e(TAG, "提示：" +retJSON.toString());
                if (code.equals("0")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    ipAddress = data.getString("ip")/* + "(" + data.getString("country")
                            + data.getString("area") + "区"
                            + data.getString("region") + data.getString("city")
                            + data.getString("isp") + ")"*/;

                    Log.e(TAG, "您的IP地址是：" + ipAddress);
                } else {
                    Log.e(TAG, "IP接口异常，无法获取IP地址！");
                }
            } else {
                Log.e(TAG, "网络连接异常，无法获取IP地址！");
            }
        } catch (Exception e) {
            Log.e(TAG, "获取IP地址时出现异常，异常信息是：" + e.toString());
        }
        return ipAddress;
    }

    @SuppressLint("MissingPermission")
    public static String getIpAddress(Context context) {
        if (context == null) {
            return "";
        }

        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo info = conManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 3/4g网络
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return getHostIp();
                } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
//                    return getLocalIPAddress(context); // 局域网地址
                    return getOutNetIP(); // 外网地址
                } else if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {
                    // 以太网有限网络
                    return getHostIp();
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";

    }

    /**
     * 1、wifi下不取ip（默认值为null）
     * 2、取外网ip，如果客户端取不到外网ip，就传"null"
     * @param context
     * @parm  handler 异步传输处理
     */
    public static String  getIp(Context context, Handler handler){
        Date startTime=new Date();
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL infoUrl = null;
                InputStream inStream = null;
                String outIp =null;
                Looper.prepare();
                try {
                    infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
                    URLConnection connection = infoUrl.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    //设置连接超时时间10s
                    httpConnection.setConnectTimeout(10000);
                    //设置读取超时时间10s
                    httpConnection.setReadTimeout(10000);
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        inStream = httpConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                        StringBuilder strber = new StringBuilder();
                        while ((outIp = reader.readLine()) != null)
                            strber.append(outIp + "\n");
                        inStream.close();
                        // 从反馈的结果中提取出IP地址
                        int start = strber.indexOf("{");
                        int end = strber.indexOf("}");
                        String json = strber.substring(start, end + 1);
                        if (json != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                outIp = jsonObject.optString("cip");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        //todo:如何获取自线程中结果返回主线程
                        Date time=new Date();
//                        Constants.outIp=line;
                        Message message=new Message();
                        Bundle bundle=new Bundle();
                        bundle.putString("outip", outIp);
                        message.setData(bundle);//bundle传值，耗时，效率低
                        handler.sendMessage(message);//发送message信息
                        message.what=1001;//标志是哪个线程传数据
                        //补齐异步操作导致埋点数据  部分参数未赋值问题
                        TouchData.reSetProp(startTime,time,"_ip",outIp);
                        ToastTools.showCenterToast(context,"outIp:"+outIp);
                        Logs.e("TOUCH-outIp",outIp);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return null;
    }




}
