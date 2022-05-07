package com.tengfei.fairy.designPattern.chanResponsibily.handle;

import android.app.Activity;
import android.content.Intent;

import com.tengfei.fairy.designPattern.chanResponsibily.AfterLoginCheckActivity;
import com.tengfei.fairy.designPattern.chanResponsibily.ChainHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.UserInfoVo;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :常用设备校验Handler
 * @ Author 李腾飞
 * @ Time 2022/5/6   5:36 PM
 * @ Version :
 */
public class LoginOftenHandler extends ChainHandler {
    public static String TAG= LoginOftenHandler.class.getSimpleName();

    @Override
    public void handleRequest(Activity activity, UserInfoVo userInfoVo) {
        Logs.d(TAG,"ltf-常用设备校验开始处理");
         if (!userInfoVo.getIsOften().equals("often")){
             Logs.d(TAG,"ltf-常用设备校验不通过，进一步处理开始");
             Intent intent=new Intent(activity, AfterLoginCheckActivity.class);
             activity.startActivity(intent);
         }else {
             Logs.d(TAG,"ltf-常用设备校验通过");
             if (getNext()!=null){
                 getNext().handleRequest(activity,userInfoVo);
             }else {
                 Logs.d(TAG,"ltf-常用设备校验后无其他处理者处理");
             }
         }
    }
}
