package com.tengfei.fairy.Kotlin

/**
 * @Description :扩展
 * 以成员的形式定义的扩展函数, 可以声明为 open , 而且可以在子类中覆盖. 也就是说,
 * 在这类扩展函数的派 发过程中, 针对分发接受者是虚拟的(virtual), 但针对扩展接受者仍然是静态的。
 * @Auther tengfei.li
 * @Since 2022/8/4 14:25
 * @Version 1.0
 */
class Exend {
}

open class D0 {
}

class D1 : D0() {
}

open class C0 {
    open fun D0.foo() {
        println("D0.foo in C0")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: D0) {
        d.foo()   // 调用扩展函数
    }
}

class C1 : C0() {
    override fun D0.foo() {
        println("D0.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}


fun main(args: Array<String>) {
    C0().caller(D0())   // 输出 "D0.foo in C"
    C1().caller(D0())  // 输出 "D0.foo in C1" —— 分发接收者虚拟解析
    C0().caller(D1())  // 输出 "D0.foo in C" —— 扩展接收者静态解析

}