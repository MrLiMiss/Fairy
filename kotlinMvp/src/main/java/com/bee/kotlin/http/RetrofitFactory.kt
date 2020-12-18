package com.bee.kotlin.http

import com.bee.kotlin.http.interceptor.HeaderInterceptor
import com.beebank.newbee.common.BuildConfig
import com.beebank.newbee.common.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform.Companion.INFO
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class RetrofitFactory<T> {
    var apiService: T
    /**
     * 日志拦截器
     */
    var loggingInterceptor = LoggingInterceptor.Builder()
            .setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE)
            .log(INFO)
            .request("request")
            .response("response")
            .build()

    init {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
//                .addInterceptor(ResponseEncryptInterceptor())
                .addInterceptor(loggingInterceptor)//设置打印得日志内容
                .connectTimeout(Constants.http.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(Constants.http.getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(Constants.http.getTimeout(), TimeUnit.SECONDS)
                .cookieJar(CookieManager())
                .build()

        apiService = Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(buildGson())) // 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Retrofit到RxJava的转换器
                .client(httpClient)
                .build()
                .create(getApiService())
    }


    abstract fun getApiService(): Class<T>
    private fun buildGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
    }

    fun getService(): T {
        return apiService
    }

    abstract fun getBaseUrl(): String
}