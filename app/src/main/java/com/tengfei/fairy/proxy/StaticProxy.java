package com.tengfei.fairy.proxy;

/**
 * @ Description :代理-静态代理
 * @ Author 李腾飞
 * @ Time 2022/3/6   3:57 PM
 * @ Version :
 */
public class StaticProxy {
    private Subreject subreject;


    public StaticProxy(Subreject subreject) {
        this.subreject = subreject;
    }

    public void text(){
        System.out.println("代理对象执行一些不方便的事情");
        subreject.text();
        System.out.println("执行结束");
    }
}
