package com.tengfei.fairy.Kotlin

import kotlin.random.Random


/**
 * @Description : 作用域函数
 * kotlin 作用域函数 apply  run with  let also
 *
 * apply：public inline fun T.apply(block: T.() -> Unit): T 。返回值 为 扩展对象
 * run： public inline fun <T, R> T.run(block: T.() -> R): R 。返回值为 函数类型的返回值
 * with： 它不是一个扩展函数。public inline fun <T, R> with(receiver: T, block: T.() -> R): R 。返回值为 函数类型的返回值
 * let： public inline fun <T, R> T.let(block: (T) -> R): R 。返回值为函数类型的返回值。
 * also： public inline fun T.also(block: (T) -> Unit): T。返回值为 扩展对象
 *
 * @Auther tengfei.li
 * @Since 2022/8/4 17:10
 * @Version 1.0
 */
class ScopeFun {
}

data class Person1(var name: String, var age: Int, var city: String) {
    fun moveTo(newCity: String) { city = newCity }
    fun incrementAge() { age++ }
}

data class Person2(var name: String, var age: Int = 0, var city: String = "")

fun writeToLog(message: String) {
    println("INFO: $message")
}

/**
 * also 操作符
 */
fun getRandomInt(): Int {
    return Random.nextInt(100).also {
        writeToLog("getRandomInt() generated value $it")
        //下面这行的this会报编译错误：'this' is not defined in this context
        //writeToLog("getRandomInt() generated value $this")
    }
}

/**
 * 从it改成value
 */
fun getRandomInt2(): Int {
    return Random.nextInt(100).also { value -> {
        writeToLog("getRandomInt() generated value $value")
        writeToLog("getRandomInt() generated 222 value $value")
    }
    }
}


/**
 * @param str1 参数1
 * @param str2 参数2
 */
fun getResult(str1: String, str2: String): String = "result is {$str1 , $str2}"

/**
 * @param p1 参数1
 * @param p2 参数2
 * @param method 方法名称
 */
fun lock(p1: String, p2: String, method: (str1: String, str2: String) -> String): String {
    return method(p1, p2)
}


fun main() {
    //let 操作符
    Person1("Alice", 20, "Amsterdam").let {
        println(it)
        it.moveTo("London")
        it.incrementAge()
        println(it)
    }

    //apply 操作符
    val adam = Person2("Adam").apply {
        age = 20                       // same as this.age = 20 or adam.age = 20
        city = "London"                //但是不加this，就不容易区分age、city是来自内部接收器Person    还是   外部成员或功能
    }
    println(adam)

    //also 操作符
    val i = getRandomInt()
    println(i)
    val j = getRandomInt2()
    println(j)

    // :: 测试双冒号操作符 表示把一个方法当做一个参数    ，:: 调用的函数如果是类的成员函数或者是扩展函数，必须使用限定符,比如this
    println(lock("param1", "param2", ::getResult))



}
