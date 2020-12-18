package com.bee.kotlin.http.interceptor

import com.beebank.newbee.common.BuildConfig
import com.beebank.newbee.common.CommonConfig
import com.beebank.newbee.common.Constants
import com.beebank.newbee.common.utils.Utils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author Leo
 * @date 2020/4/3
 * 拦截器添加header
 * //                        "application/json/x-www-form-urlencoded;charset=UTF-8;multipart/form-data"

 */
class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var token: String? = CommonConfig.getServiceManager().tokenService.accessToken
        val request = original.newBuilder()
                .addHeader(
                        "Content-Type",
                        "application/json;charset=UTF-8;multipart/form-data"
                )
                .addHeader("Content-Encoding", "gzip")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", if (token.isNullOrEmpty()) "" else token)
                .addHeader("x-version", BuildConfig.VERSION_NAME)
                .build()
        return chain.proceed(request)
    }
}