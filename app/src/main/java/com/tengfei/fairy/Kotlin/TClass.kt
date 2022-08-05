package com.tengfei.fairy.Kotlin

/**
 * @Description :kotlin 泛型相关
 * @Auther tengfei.li
 * @Since 2022/8/4 16:01
 * @Version 1.0
 */
class TClass {
}


//泛型类 Box
class Box<T>(t : T) {
    var value = t
}

//定义泛型类型变量，可以完整地写明类型参数，如果编译器可以自动推定类型参数，也可以省略类型参数。


fun <T> boxIn(value: T) = Box(value)


//泛型函数，根据类型不同返回不同
fun <T> doPrintln(content: T) {

    when (content) {
        is Int -> println("整型数字为 $content")
        is String -> println("字符串转换为大写：${content.toUpperCase()}")
        else -> println("T 不是整型，也不是字符串")
    }
}


fun main(args: Array<String>) {
    //泛型
    var boxInt = Box<Int>(10)
    var boxString = Box<String>("Runoob")
    println(boxInt.value)
    println(boxString.value)

    //Kotlin 泛型函数的声明与 Java 相同，类型参数要放在函数名的前面：
    // 以下都是合法语句
    val box4 = boxIn<Int>(1)
    val box5 = boxIn(1)     // 编译器会进行类型推断
    println(box4.value)
    println(box5.value)


    //泛型函数处理
    val age = 23
    val name = "runoob"
    val bool = true
    doPrintln(age)    // 整型
    doPrintln(name)   // 字符串
    doPrintln(bool)   // 布尔型
}