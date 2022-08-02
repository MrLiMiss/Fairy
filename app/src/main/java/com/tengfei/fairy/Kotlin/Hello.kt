package com.tengfei.fairy.Kotlin

/**
 * @Description :
 * @Auther tengfei.li
 * @Since 2022/7/29 17:56
 * @Version 1.0
 */
class Hello {
    val a: Int = 1
    val b = 1


    fun greet() {
        val c: Int = 4
        var x = 54
        x += 1
        println("fun test$x")
    }

    //表达式作为函数体，返回类型自行判断
    fun sum(a: Int, b: Int) = a + b

    //public 类型方法必须明确返回类型
    public fun sum3(a: Int, b: Int): Int = a + b

    //无返回值类型的函数
    fun printSum(a: Int, b: Int): Unit {
        println(a + b)
    }

    //如果返回类型是Unit类型，则可以省略（public方法也是这样）：
    public fun printSum2(a: Int, b: Int) {
        println(a + b)
    }

    //可变长参数函数
    fun vars(vararg v: Int) {
        for (vt in v) {
            print(vt)
        }
        println()
    }

    //lambada 表达式
    fun lambada() {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println(sumLambda(5, 5))
    }

    // String  字符串模板
    fun stringMode() {
        var a = 1
        val s1 = "s1 is $a"
        a = 2
        val s2 = "${s1.replace("is", "was")},but now is $a"
        println(s1)
        println(s2)
    }

    // null 检查机制
    fun nullTest(){
        //类型后面加？表示可为空
        var age: String? = "23"
        //抛出空指针异常
        val arges = age!!.toInt()

        //不做处理返回NULL
        val ages1 = age?.toInt()

        //age为null返回-1
        val ages2 = age?.toInt() ?: -1

    }

    //当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null。
    fun parseInt(str: String): Int? {
        return str.toIntOrNull()
    }

    fun parseInt2(str: String): Int? {
       // 如果str为null，不做处理返回 null
        return str?.toIntOrNull()
    }

    // is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字
    fun isTest(obj:Any):Int?{
        if (obj is String){
            //做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        }
        return null
    }


    //区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
    fun  ranageTest(i:Int){
        for (i in 1..4) print(i) // 输出“1234”

        for (i in 4..1) print(i) // 什么都不输出

        if (i in 1..10) { // 等同于 1 <= i && i <= 10
            println(i)
        }

        // 使用 step 指定步长
        for (i in 1..4 step 2) print(i) // 输出“13”

        for (i in 4 downTo 1 step 2) print(i) // 输出“42”


        // 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            println(i)
        }
    }


}

fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数
    println("Hello World!")         // 分号可以省略
    Hello().greet()
    println(Hello().sum(2, 5))
    println(Hello().sum3(1, 1))
    Hello().printSum(2, 2)
    Hello().printSum2(3, 3)
    Hello().vars(1, 2, 3, 4, 5, 6, 7, 8)
    Hello().lambada()
    Hello().stringMode()
    Hello().ranageTest(9)


}
