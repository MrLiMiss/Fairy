package com.tengfei.fairy.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @ Description : 实体转换器
 * @ Author 李腾飞
 * @ Time 2020-11-17   10:36
 * @ Version :
 */
public class GsonFactory extends Converter.Factory {
    private Gson gson;

    public static GsonFactory create(Gson gson) {
        if (null == gson) {
            throw new NullPointerException("gson is null");
        }
        return new GsonFactory(gson);
    }

    private GsonFactory(Gson gson) {
        this.gson = gson;
    }


    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new RequestBodyConverter(gson, adapter);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ResponseBodyConverter(gson,type);
    }
}
