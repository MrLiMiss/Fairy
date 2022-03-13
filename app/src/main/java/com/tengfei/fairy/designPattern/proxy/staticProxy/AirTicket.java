package com.tengfei.fairy.designPattern.proxy.staticProxy;

/**
 * @ Description :定义飞机场售票
 * @ Author 李腾飞
 * @ Time 2022/3/13   8:49 PM
 * @ Version :
 */
public class AirTicket implements IAirTicket {
    @Override
    public void buy() {
        System.out.println("买票");
    }
}
