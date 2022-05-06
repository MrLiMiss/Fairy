package com.tengfei.fairy.designPattern.chanResponsibily;

import android.app.Activity;

/**
 * @ Description :责任链模式 处理者
 * @ Author 李腾飞
 * @ Time 2022/5/6   5:33 PM
 * @ Version :
 */
public abstract class ChainHandler {
    private ChainHandler next;
    public void setNext(ChainHandler next){
        this.next=next;
    }

    public ChainHandler getNext(){
        return next;
    }

    public abstract void handleRequest(Activity activity,UserInfoVo userInfoVo);
}
