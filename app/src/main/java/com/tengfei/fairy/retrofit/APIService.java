package com.tengfei.fairy.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ Description :（现有为）埋点数据 请求数据接口
 * @ Author 李腾飞
 * @ Time 2020-11-17   13:44
 * @ Version :
 */
public interface APIService {

    @POST("data/encryption")
    Call<DataSubject> PostEncryptionData(@Body OnEventRequest req);

    //获取时间差值
    @GET("clock")
    Call<TimeDifferenceBean> geTimeDifference(@Query("t") String timeDifference);

}

