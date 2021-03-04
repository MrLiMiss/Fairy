package com.tengfei.fairy.javaBase.http.okhttp;

import com.tengfei.fairy.sectury.MD5;

import java.io.IOException;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-16   13:55
 * @ Version :
 */
public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        long timeStamp = new Date().getTime();
        String appkey="";//项目ID
        String appsecret="";//
        String time = String.valueOf(timeStamp);
        String tokenData = time + "_" + appkey + "_" + appsecret;

        String token = null;
        try {
            token = MD5.getMD5(tokenData.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加请求参数
        HttpUrl httpurl = oldRequest.url().newBuilder()
                .addQueryParameter("appkey", "default")
                .addQueryParameter("token", token)
                .addQueryParameter("t", time)
                .build();
        //添加请求头
        Request newRequest = oldRequest
                .newBuilder()
                .header("Content-Type", "application/json;charset=utf-8")      //ltf123
                .url(httpurl)
                .build();
//            Logs.d("Hrbbdata-url", httpurl.toString());

        Response response = chain.proceed(newRequest);

        return response;
    }
}
