package com.tengfei.fairy.designPattern.proxy.dynamicproxy;

/**
 * @ Description :动态代理
 * @ Author 李腾飞
 * @ Time 2022/3/6   4:10 PM
 * @ Version :
 */
public class ProxyText {

    public static void main(String[] args) {
        Subreject subreject = new Subreject();
        StaticProxy proxy = new StaticProxy(subreject);
        proxy.text();

    }
}
