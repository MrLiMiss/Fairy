package com.tengfei.fairy.Kotlin

/**
 * @Description :
 * Kotlin  it  this 测试
 * this 用于 带接收者 的 函数类型 ，表示接收者 。
 * it 用于 函数类型中： 函数只有一个参数 。 it表示 参数对象。
 * @Auther tengfei.li
 * @Since 2022/8/4 17:01
 * @Version 1.0
 */
class ItTest {
}
open class Person(var name: String, var age: Int) {
    fun doSomeThings(){
        print(" just so so ")
    }
}

fun <T : Person> T.testThis(f: T.() -> Unit) {
    //带接收者的扩展函数可以直接调用。无需指定接收者
    f()
}
fun <T : Person> T.testIt(f: (T) -> Unit) {
    f(this)
}
// 无法将扩展对象和 函数类型参数联系起来
fun <T : Person> T.testWithOutArg(f: () -> Unit) {
}

fun main() {
    var p = Person(" 小黑",20)
    p.testThis { this.doSomeThings() }
    p.testIt { it.doSomeThings() }

}
