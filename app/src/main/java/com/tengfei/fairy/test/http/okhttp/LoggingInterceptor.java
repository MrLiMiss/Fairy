package com.tengfei.fairy.test.http.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ Description :okhttp3 添加全局 全局过滤器，
 * 在整个拦截器链中最早被调用，通过 OkHttpClient.Builder#addInterceptor(Interceptor) 传入；
 * @ Author 李腾飞
 * @ Time 2020-11-16   10:55
 * @ Version :
 */
public class LoggingInterceptor implements Interceptor {

    private static final String TAG = "LoggingInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Long startTime=System.nanoTime();
        System.out.print(TAG+" " +String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        Response response =  chain.proceed(request);

        long endTime = System.nanoTime();
        System.out.print(TAG+" "+String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (endTime - startTime) / 1e6d, response.headers()));

        return response;
    }

}
