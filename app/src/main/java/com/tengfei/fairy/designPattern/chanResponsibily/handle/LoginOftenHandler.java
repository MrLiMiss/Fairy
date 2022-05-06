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
         if (!userInfoVo.getIsOften().equals("Often")){
             Logs.d(TAG,"ltf-常用设备校验开始处理");
             Intent intent=new Intent(activity, AfterLoginCheckActivity.class);
             activity.startActivity(intent);
         }else {
             if (getNext()!=null){
                 getNext().handleRequest(activity,userInfoVo);
             }else {
                 Logs.d(TAG,"ltf-常用设备校验后无其他处理者处理");
             }
         }
    }
}
