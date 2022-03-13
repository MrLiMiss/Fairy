package com.tengfei.fairy.designPattern.decorateion;

/**
 * @ Description : 定义具体装饰器 戴帽子
 * @ Author 李腾飞
 * @ Time 2022/3/13   8:39 PM
 * @ Version :
 */
class Hat extends DecoratorBase {
    public Hat(IPerson iPerson) {
        super(iPerson);
    }
    @Override
    public void show() {
        // 执行已有功能
        iPerson.show();
        // 定义新行为
        System.out.println("戴上帽子");
    }
}
