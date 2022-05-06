package com.tengfei.fairy.designPattern.strategy;

/**
 * @ Description :指纹登录策略
 * @ Author 李腾飞
 * @ Time 2022/5/5   10:21 AM
 * @ Version :
 */
public class FingerLogin implements LoginStrategy {
    public static String TAG=FingerLogin.class.getSimpleName();


    @Override
    public void login(String loginJson) {
       System.out.println(TAG+":"+"指纹登录"+loginJson);
    }
}
