package com.tengfei.fairy.designPattern.strategy;


/**
 * @ Description :包装策略的类（运行上下文）
 * @ Author 李腾飞
 * @ Time 2022/5/5   10:27 AM
 * @ Version :
 */
public class LoginContext {
    LoginStrategy loginStrategy;

    public LoginStrategy getLoginStrategy(){
        return  loginStrategy;
    }


    public void setLoginStrategy(LoginStrategy loginStrategy){
        this.loginStrategy=loginStrategy;
    }

    public void login(String loginJson){
        if (loginStrategy!=null){
            loginStrategy.login(loginJson);
        }
    }
}
