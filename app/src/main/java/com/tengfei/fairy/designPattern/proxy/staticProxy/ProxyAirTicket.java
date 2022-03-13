package com.tengfei.fairy.designPattern.proxy.staticProxy;

/**
 * @ Description :代理   售票平台
 * @ Author 李腾飞
 * @ Time 2022/3/13   8:48 PM
 * @ Version :
 */
public class ProxyAirTicket implements IAirTicket {
    private AirTicket airTicket;
    public ProxyAirTicket() {
        airTicket = new AirTicket();
    }
    @Override
    public void buy() {
        airTicket.buy();
    }
}
