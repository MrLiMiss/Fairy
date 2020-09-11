package com.tengfei.fairy.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tengfei.fairy.application.FairyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ Description :SharePreference 工具类
 * @ Author 李腾飞
 * @ Time 2020-08-04   10:37
 * @ Version :
 */
public class SharePreferenceUtil {
    /**
     * 保存在手机里面的文件名
     */
    private static final String SP_NAME ="SP_TENGFEI_DATA";

    private static SharedPreferences preferences =FairyApplication.getApplication()
            .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

    public static String getInfoFromShared(String key) {
        return preferences.getString(key, null);
    }

    public static String getInfoFromShared(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public static boolean setInfoToShared(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
        return true;
    }

    public static boolean removeShared(String key){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
        return true;
    }
    /**
     *   @date 创建时间:2017/5/27
     *   @author chenbin
     *   @description 存入与取出boolean类型数据
     *   @version
     */
    public static boolean getBooleanInfoFromShared(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }
    public static boolean setBooleanInfoToShared(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
        return true;
    }
}