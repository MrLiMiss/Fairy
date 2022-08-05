package com.tengfei.fairy.Kotlin

/**
 * @Description :  companion object
 * @Auther tengfei.li
 * @Since 2022/8/4 13:57
 * @Version 1.0
 */
class Hoge {
    object A {
        val fizz = "fizz"
        fun foo() {
            println("object A ---fun   foo()")
        }
    }

    companion object {
        val buzz = "buzz"
        fun bar() {
            println("companion object ---fun   bar()")
        }
    }



}
fun main(arg:Array<String>) {

        // 通常的object
       var s1= Hoge.A.fizz
        println(s1)
        Hoge.A.foo()

        // companion object
        var s2= Hoge.buzz
       println(s2)
        Hoge.bar()


}