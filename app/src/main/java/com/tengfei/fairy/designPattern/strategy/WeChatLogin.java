package com.tengfei.fairy.designPattern.strategy;

import com.tengfei.fairy.utils.Logs;

import org.json.JSONObject;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/5/5   10:34 AM
 * @ Version :
 */
class WeChatLogin implements LoginStrategy{
    public static String TAG=WeChatLogin.class.getSimpleName();
    @Override
    public void login(String loginJson) {
        System.out.println(TAG+":"+"微信登录"+loginJson);
    }
}
