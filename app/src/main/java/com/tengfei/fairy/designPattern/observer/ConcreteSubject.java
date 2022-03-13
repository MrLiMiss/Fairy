package com.tengfei.fairy.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Description :具体被观察者（消息发布方）
 * @ Author 李腾飞
 * @ Time 2022/3/13   6:21 PM
 * @ Version :
 */
public class ConcreteSubject implements Subject {
    // 订阅者列表（存储信息）
    private List<Observer> list = new ArrayList<Observer>();
    @Override
    public void attach(Observer observer) {
        list.add(observer);
    }
    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }
    @Override
    public void notify(String message) {
        for (Observer observer : list) {
            observer.update(message);
        }
    }
}
