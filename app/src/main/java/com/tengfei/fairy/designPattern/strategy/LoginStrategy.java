package com.tengfei.fairy.designPattern.strategy;

import org.json.JSONObject;

/**
 * @ Description :登录策略
 * @ Author 李腾飞
 * @ Time 2022/5/5   10:19 AM
 * @ Version :
 */
public interface LoginStrategy {
    void login(String loginJson);
}
