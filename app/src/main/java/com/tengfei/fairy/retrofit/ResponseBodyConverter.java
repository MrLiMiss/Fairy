package com.tengfei.fairy.retrofit;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 返回结果拦截器
 * @param <T>
 */
public class ResponseBodyConverter<T> implements Converter<ResponseBody, BaseBean<T>> {
    Type type;
    Gson gson;

    public ResponseBodyConverter(Gson gson, Type type) {
        this.type = type;
        this.gson = gson;
    }

    @Override
    public BaseBean<T> convert(ResponseBody value) throws IOException {
        String json = value.string();
        BaseBean<T> bean = new Gson().fromJson(json,type);
        return bean;
    }
}
