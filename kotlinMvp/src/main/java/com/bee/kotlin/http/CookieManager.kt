package com.bee.kotlin.http

import com.bee.kotlin.Constants
import com.bee.kotlin.helper.SharedPrefHelper
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

/**
 * @author xianmei.li
 * @date 2018/9/17
 */
class  CookieManager : CookieJar {
    override fun saveFromResponse(
        httpUrl: HttpUrl,
        list: List<Cookie>
    ) {
        SharedPrefHelper()
            .setCookieList(Constants.COOKIE, list)
    }

    override fun loadForRequest(httpUrl: HttpUrl): List<Cookie> {
        val mListCookies: List<Cookie>? =
            SharedPrefHelper()
                .getCookieList(Constants.COOKIE, Constants.COOKIE_PREFERENCE)
        return mListCookies ?: ArrayList()
    }
}