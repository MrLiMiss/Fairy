package com.tengfei.fairy.designPattern.chanResponsibily.handle;

import android.app.Activity;
import android.content.Intent;

import com.tengfei.fairy.designPattern.chanResponsibily.AfterLoginCheckActivity;
import com.tengfei.fairy.designPattern.chanResponsibily.ChainHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.UserInfoVo;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :九要素校验逻辑处理
 * @ Author 李腾飞
 * @ Time 2022/5/6   9:40 PM
 * @ Version :
 */
public class NineElementHandler extends ChainHandler {
    public static String TAG= NineElementHandler.class.getSimpleName();
    @Override
    public void handleRequest(Activity activity, UserInfoVo userInfoVo) {
        Logs.d(TAG,"ltf-九要素校验开始处理");

        if (!userInfoVo.getNineElement().equals("fullNineElement")){
            Logs.d(TAG,"ltf-九要素校验不通过，进行进一步处理补充");
            Intent intent=new Intent(activity, AfterLoginCheckActivity.class);
            activity.startActivity(intent);
        }else {
            Logs.d(TAG,"ltf-9要素校验通过");
            if (getNext()!=null){
                getNext().handleRequest(activity,userInfoVo);
            }else {
                Logs.d(TAG,"ltf-九要素校验后无其他处理者处理");
            }

        }
    }
}
