package com.tengfei.fairy.designPattern.observer;

/**
 * @ Description :具体观察者（消息接收方）
 * @ Author 李腾飞
 * @ Time 2022/3/13   6:17 PM
 * @ Version :
 */
public class ConcrereObserver  implements Observer{
    private String name;

    public ConcrereObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "：" + message);
    }

}
