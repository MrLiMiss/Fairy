package com.tengfei.fairy.designPattern.observer;

/**
 * @ Description :被观察者（消息发布放）
 * @ Author 李腾飞
 * @ Time 2022/3/13   6:20 PM
 * @ Version :
 */
public interface Subject {
    // 增加订阅者
    public void attach(Observer observer);
    // 删除订阅者
    public void detach(Observer observer);
    // 通知订阅者更新消息
    public void notify(String message);
}
