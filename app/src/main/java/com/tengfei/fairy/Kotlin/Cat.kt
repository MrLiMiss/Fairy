package com.tengfei.fairy.Kotlin

/**
 * @Description :
 * 若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
 * @Auther tengfei.li
 * @Since 2022/8/4 10:13
 * @Version 1.0
 */
class Cat {

    fun foo() { println("成员函数") }
}

fun Cat.foo() { println("扩展函数") }


//在扩展函数内， 可以通过 this 来判断接收者是否为 NULL,这样，即使接收者为 NULL,也可以调用扩展函数。
fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}




fun main(arg:Array<String>){
    //1.若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
    var c = Cat()
    c.foo()//输出结果成员函数

   //2.即使接收者为 NULL,也可以调用扩展函数。
    var t = null
    println(t.toString())//输出结果null

}
