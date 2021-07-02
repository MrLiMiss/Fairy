package com.tengfei.fairy.retrofit;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.utils.Base64Util;
import com.tengfei.fairy.utils.Logs;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @ Description :请求体转换类
 * @ Author 李腾飞
 * @ Time 2020-04-08   16:16
 * @ Version :
 */
public class RequestBodyConverter<T> implements Converter<OnEventRequest, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType
            .parse("application/json; charset=UTF-8");

    /*private static final MediaType MEDIA_TYPE = MediaType
            .parse("text/plain; charset=utf-8");*/

    final Gson gson;
    final TypeAdapter<T> adapter;

    RequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }



    // 加解转换
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public RequestBody convert(OnEventRequest value) throws IOException {

        String json = gson.toJson(value.eventList);
        Logs.d("Hrbbdata-加密前data-json：" ,json);
        String transJson=json.replaceAll("\"null\"","null");
        Logs.d("Hrbbdata-加密前转换后data-transJson：" ,transJson);
        if(value.cmd.equals(Constants.ENCRYPTION)){//加密传输
            transJson= Base64Util.encode(transJson);
            Logs.d("Hrbbdata-加密后json：",transJson);
        }

        return RequestBody.create(MEDIA_TYPE, transJson);
    }
}
