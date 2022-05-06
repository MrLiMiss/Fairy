package com.tengfei.fairy.designPattern.strategy;

/**
 * @ Description :策略设计模式实践
 * @ Author 李腾飞
 * @ Time 2022/5/5   10:43 AM
 * @ Version :
 */
public class MyLoginTest {
    public static void main(String[] args) {
        try{
            LoginContext loginContext=new LoginContext();
            loginContext.setLoginStrategy(new FingerLogin());
            loginContext.login("finger");
            loginContext.setLoginStrategy(new VoiceLogin());
            loginContext.login("voice");
            loginContext.setLoginStrategy(new WeChatLogin());
            loginContext.login("weChat");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
