package com.tengfei.fairy.Kotlin

/**
 * @Description :kotlin 类和对象 测试
 * @Auther tengfei.li
 * @Since 2022/8/2 14:41
 * @Version 1.0
 */
class ClassObjectTest {
    var name: String = "oumeng test"
        get() = field.toUpperCase() //将变量赋值后转换为大写
        set

    var url: String = "www.oumeng.com"

    var city: String = "BeiJing China"

    var age: Int = 22
        get() = field                //后端处理
        set(value) {
            if (value < 10) {       // 如果传入的值小于 10 返回该值
                field = -1
            } else {
                field = value        // 如果传入的值大于等于 10 返回 -1
            }
        }

    var heiht: Float = 145.4f
        private set


    //主构造器
    class ClassObjectTest constructor(name: String, url: String, city: String) {}

    //次构造函数
    constructor(name:String){
        println(name)
    }
}

fun main(args: Array<String>) {
    var classObjectTest: ClassObjectTest = ClassObjectTest("构造器测试类")
    classObjectTest.age = 32
    println("age:${classObjectTest.age}")

    classObjectTest.city = "朝阳"
    println("city:${classObjectTest.city}")

}