package com.tengfei.fairy.Kotlin

/**
 * @Description :kotlin 数据类 密封类
 * @Auther tengfei.li
 * @Since 2022/8/4 15:32
 * @Version 1.0
 */
class DataTriple {
}

//数据类
data class UserData(val name: String, val age: Int)


//密封类,声明一个密封类，使用 sealed 修饰类，密封类可以有子类，但是所有的子类都必须要内嵌在密封类中。
//sealed 不能修饰 interface ,abstract class(会报 warning,但是不会出现编译错误)
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}


fun main(args: Array<String>) {
    //数据类：复制使用 copy() 函数，我们可以使用该函数复制对象并修改部分属性,
    val jack = UserData(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println(jack)
    println(olderJack)

    //组件函数允许数据类在解构声明中使用：
    val jane = UserData("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // prints "Jane, 35 years of age"



}