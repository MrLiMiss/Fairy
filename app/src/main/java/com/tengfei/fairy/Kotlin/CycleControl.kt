package com.tengfei.fairy.Kotlin

/**
 * @Description :循环控制
 * @Auther tengfei.li
 * @Since 2022/8/2 13:23
 * @Version 1.0
 */
class CycleControl {

    /**
     * while控制
     */
    fun whileTest() {
        println("----while 使用-----")
        var x = 5
        while (x > 0) {
            println(x--)
        }
        println("----do...while 使用-----")
        var y = 5
        do {
            println(y--)
        } while (y > 0)

    }

    /**
     * Kotlin 有三种结构化跳转表达式：
     * return。默认从最直接包围它的函数或者匿名函数返回。
     * break。终止最直接包围它的循环。
     * continue。继续下一次最直接包围它的循环。
     */
    fun testBreak() {
        for (i in 1..10) {
            if (i == 3) continue  // i 为 3 时跳过当前循环，继续下一次循环
            println(i)
            if (i > 5) break   // i 为 6 时 跳出循环
        }
    }

   // 标签使用
    fun forTest() {

        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (i == 1 && j == 10) {
                    break@loop  //直接跳出标签
                }
                println("----标签 使用--i=${i},j=${j}--")
            }
        }
    }

    fun foo() {
        print("测试 lambda 标签")
        var ints = intArrayOf(1,2,3,99,3,0)
        ints.forEach(fun(value: Int) {
            if (value == 0) return
            print(value)
        })
    }


}


fun main(args: Array<String>) {

    CycleControl().whileTest()
    CycleControl().testBreak()
    CycleControl().forTest()
    CycleControl().foo()

}