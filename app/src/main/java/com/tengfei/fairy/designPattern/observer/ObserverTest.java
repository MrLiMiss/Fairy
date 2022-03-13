package com.tengfei.fairy.designPattern.observer;

/**
 * @ Description :观察者模式
 *      观察者模式是定义对象间的一种一对多依赖关系，使得每当一个对象状态发生改变时，其相关依赖对象皆得到通知并被自动更新。
 *      观察者模式又叫做发布-订阅（Publish/Subscribe）模式、模型-视图（Model/View）模式、源-监听器（Source/Listener）模式或从属者（Dependents）模式。
 *
 *      优点：
 *      1、观察者模式可以实现表示层和数据逻辑层的分离，并定义了稳定的消息更新传递机制，抽象了更新接口，使得可以有各种各样不同的表示层作为具体观察者角色；
 *      2、观察者模式在观察目标和观察者之间建立一个抽象的耦合；
 *      3、观察者模式支持广播通信；
 *      4、观察者模式符合开闭原则（对拓展开放，对修改关闭）的要求。
 *      缺点：
 *      1、如果一个观察目标对象有很多直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间；
 *      2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃；
 *      2、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 * @ Author 李腾飞
 * @ Time 2020-11-27   15:38
 * @ Version :
 */
public class ObserverTest {
    public static void main(String[] args) {
        // 定义发布者
        ConcreteSubject concreteSubject = new ConcreteSubject();
        // 定义订阅者
        ConcrereObserver concrereObserver = new ConcrereObserver("老王");
        ConcrereObserver concrereObserver2 = new ConcrereObserver("Java");
        // 添加订阅
        concreteSubject.attach(concrereObserver);
        concreteSubject.attach(concrereObserver2);
        // 发布信息
        concreteSubject.notify("更新了");
    }
}
