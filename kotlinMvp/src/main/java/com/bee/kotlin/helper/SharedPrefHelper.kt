package com.bee.kotlin.helper

import android.content.Context
import android.content.SharedPreferences
import com.beebank.newbee.common.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Cookie
import java.util.*

class SharedPrefHelper {
    var mContext: Context? = null

    private val PERFERENCENAME = Constants.storage.PREFERENCES_FILE

    fun getPreferences(): SharedPreferences? {
        return mContext?.getSharedPreferences(PERFERENCENAME, 0)
    }


    fun setCookieList(
            key: String?,
            datalist: List<Cookie?>?
    ) {
        if (null == datalist || datalist.size <= 0) {
            return
        }
        val gson = Gson()
        //转换成json数据，再保存
        val strJson = gson.toJson(datalist)
        putString(key, strJson)
    }

    fun getCookieList(
            key: String?,
            xmlname: String?
    ): List<Cookie>? {
        var datalist: List<Cookie> = ArrayList()
        val strJson: String =
                getString(key, xmlname)
                        ?: return datalist
        val gson = Gson()
        datalist = gson.fromJson(
                strJson,
                object : TypeToken<List<Cookie?>?>() {}.type
        )
        return datalist
    }

    fun putString(
            key: String?,
            value: String?
    ): Boolean? {
        return getPreferences()?.edit()
                ?.putString(key, value)?.commit()
    }

    fun getString(key: String?): String? {
        return getString(key, "")
    }

    fun getString(
            key: String?,
            defaultValue: String?
    ): String? {
        return getPreferences()?.getString(key, defaultValue)
    }
}