package com.tengfei.fairy.designPattern.decorateion;

/**
 * @ Description :装饰模式
 *   装饰器模式是指动态地给一个对象增加一些额外的功能，同时又不改变其结构。
 *      优点：
 *      装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
 *      装饰器模式的关键：装饰器中使用了被装饰的对象。
 * @ Author 李腾飞
 * @ Time 2020-11-27   15:39
 * @ Version :
 */
public class DecorationTest {

    public static void main(String[] args) {
        //装饰模式调用
        LaoWang laoWang = new LaoWang();
        Jacket jacket = new Jacket(laoWang);
        Hat hat = new Hat(jacket);
        hat.show();
    }
}
