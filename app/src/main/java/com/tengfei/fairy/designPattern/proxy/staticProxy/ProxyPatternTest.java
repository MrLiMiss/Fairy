package com.tengfei.fairy.designPattern.proxy.staticProxy;

/**
 * @ Description :代理模式（静态代理）
 *   代理模式是给某一个对象提供一个代理，并由代理对象控制对原对象的引用。
 *
 *      优点：
 *      1、代理模式能够协调调用者和被调用者，在一定程度上降低了系统的耦合度；
 *      2、可以灵活地隐藏被代理对象的部分功能和服务，也增加额外的功能和服务。
 *      缺点：
 *      1、由于使用了代理模式，因此程序的性能没有直接调用性能高；
 *      2、使用代理模式提高了代码的复杂度。
 * @ Author 李腾飞
 * @ Time 2020-11-27   15:44
 * @ Version :
 */
public class ProxyPatternTest {
    public static void main(String[] args) {
        IAirTicket airTicket = new ProxyAirTicket();
        airTicket.buy();
    }
}
