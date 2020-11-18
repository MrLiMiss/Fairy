package com.tengfei.fairy.retrofit;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tengfei.fairy.test.http.okhttp.CommonInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @ Description :Retrofit 工具类
 * @ Author 李腾飞
 * @ Time 2020-11-17   10:32
 * @ Version :
 */
public class RetrofitFactory {
    //<editor-fold desc="属性">
    private static Retrofit sRetrofit;
    private static final long TIME_OUT_IN_SECONDS = 10;
    //</editor-fold>

    static {
        changeAPI();
    }

    private RetrofitFactory() {

    }

    @NonNull
    public static Retrofit getRetrofit() {
        return sRetrofit;
    }

    @SuppressWarnings("WeakerAccess")
    private synchronized static void changeAPI() {
        Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_HTTPS_SERVER)
                .client(new OkHttpClient.Builder()
                                // 连接超时时间
                                .connectTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                                // 读取超时时间
                                .readTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                                // 写入超时时间
                                .writeTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                                // 拦截器
//                        .addInterceptor(OkHttpInterceptors.logging())
                                .addInterceptor(new CommonInterceptor())
                                .retryOnConnectionFailure(true)
                                //.addInterceptor(OkHttpInterceptors.headers())
                                // https 处理(证书认证)
                                .sslSocketFactory(createSSLSocketFactory(), new TrustManager())
                                //verify函数效验服务器主机名
                                /*.hostnameVerifier(new HostnameVerifier() {
                                    @SuppressLint("BadHostnameVerifier")
                                    @Override
                                    public boolean verify(String hostname, SSLSession session) {
                                        return true;
                                    }
                                })*/
                                .build()
                )
                //添加实体转换工具 本项目为gson  也可为其他
                .addConverterFactory(GsonFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    // OkHttp 拦截器

    /**
     * OKHttp 拦截器提供类
     */
    private static final class OkHttpInterceptors {
        private OkHttpInterceptors() {

        }

        /**
         * 获取日志拦截器
         *
         * @return 日志拦截器
         */
        @NonNull
        private static Interceptor logging() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                    new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {

//                                Log.e("RetrofitFactory.class", message);

                        }
                    }
            );
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return interceptor;
        }

        /**
         * 获取 Header 拦截器
         *
         * @return Header 拦截器
         */
        /*private static Interceptor headers() {
            return new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    // sid
                    String sid = SignInUser.getInstance().getSessionId();
                    if (!TextUtils.isEmpty(sid)) {
                        builder.addHeader("sid", sid);
                    }
                    // tm
                    builder.addHeader("tm", String.valueOf(System.currentTimeMillis()));
                    // bssid
                    String bssid = DeviceUtils.getWifiBSSID(CourserApplication.getContext());
                    if (bssid != null) {
                        builder.addHeader("bssid", bssid);
                    }
                    // os
                    builder.addHeader("os", "Android");
                    // version
                    String version = DeviceUtils.getPackageVersion(CourserApplication.getContext());
                    if (version != null) {
                        builder.addHeader("version", version);
                    }
                    // imei
                    String imei = DeviceUtils.getDeviceId(CourserApplication.getContext());
                    if (imei != null) {
                        builder.addHeader("imei", imei);
                    }
                    // mac
                    String mac = DeviceUtils.getMac(CourserApplication.getContext());
                    if (mac != null) {
                        builder.addHeader("mac", mac);
                    }
                    // osversion
                    String osversion = DeviceUtils.getOSversion();
                    if (osversion != null) {
                        builder.addHeader("osversion", osversion);
                    }
                    // manufacturer
                    String manufacturer = DeviceUtils.getManufacturer();
                    if (manufacturer != null) {
                        builder.addHeader("manufacturer", DeviceUtils.getManufacturer());
                    }
                    // model
                    String model = DeviceUtils.getModel();
                    if (model != null) {
                        builder.addHeader("model", model);
                    }
                    // 屏幕分辨率
                    final int[] resolution = DeviceUtils.getScreenResolution(CourserApplication.getContext());
                    final String strResolution = String.format(
                            Locale.getDefault(),
                            "%dx%d",
                            Math.min(resolution[0], resolution[1]), Math.max(resolution[0], resolution[1])
                    );
                    builder.addHeader("resolution", strResolution);
                    // client
                    builder.addHeader("client", "WeiKeAPP");
                    // channel
                    String channel = AppUtils.getChannel(CourserApplication.getContext());
                    if (channel != null) {
                        builder.addHeader("channel", channel);
                    }
                    // push
                    //builder.addHeader("push", "xiaomi");
                    // did
                    String did = DeviceUtils.getDeviceId(CourserApplication.getContext());
                    if (did != null) {
                        builder.addHeader("did", did);
                    }
                    return chain.proceed(builder.build());
                }
            };
        }*/
    }

    // Https 处理

    /**
     * 相信全部证书
     */
    public static class TrustManager implements X509TrustManager {
        @Override
        @SuppressLint("TrustAllX509TrustManager")
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        @SuppressLint("TrustAllX509TrustManager")
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    /**
     * 创建 SSL Socket 工厂
     *
     * @return SSL Socket 工厂实例
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustManager()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            // do nothing
        }
        return ssfFactory;
    }


}
