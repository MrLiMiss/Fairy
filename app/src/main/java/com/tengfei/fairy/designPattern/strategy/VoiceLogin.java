package com.tengfei.fairy.designPattern.strategy;

import com.tengfei.fairy.utils.Logs;

import org.json.JSONObject;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/5/5   10:31 AM
 * @ Version :
 */
public class VoiceLogin implements LoginStrategy {
    public static String TAG=VoiceLogin.class.getSimpleName();
    @Override
    public void login(String loginJson) {
        System.out.println(TAG+":"+"声纹登录"+loginJson);
    }
}
