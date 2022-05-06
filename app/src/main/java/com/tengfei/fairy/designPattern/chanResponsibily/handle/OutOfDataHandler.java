package com.tengfei.fairy.designPattern.chanResponsibily.handle;

import android.app.Activity;
import android.content.Intent;

import com.tengfei.fairy.designPattern.chanResponsibily.AfterLoginCheckActivity;
import com.tengfei.fairy.designPattern.chanResponsibily.ChainHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.UserInfoVo;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :身份证有效期过期判断
 * @ Author 李腾飞
 * @ Time 2022/5/6   10:02 PM
 * @ Version :
 */
public class OutOfDataHandler extends ChainHandler {
    public static String TAG = OutOfDataHandler.class.getSimpleName();

    @Override
    public void handleRequest(Activity activity, UserInfoVo userInfoVo) {
        if (userInfoVo.isOutOfDate()) {//身份证过期
            Intent intent = new Intent(activity, AfterLoginCheckActivity.class);
            activity.startActivity(intent);
            Logs.d(TAG, "ltf-身份证过期校验开始处理");
        } else {
            if (getNext() != null) {
                  getNext().handleRequest(activity,userInfoVo);
            } else {
                Logs.d(TAG, "ltf-身份证过期校验后无其他处理者处理");
            }
        }
    }
}
