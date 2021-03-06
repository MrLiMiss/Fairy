package com.bee.kotlin.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ResponseEncryptInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(original)
    }
}