package com.tengfei.fairy.Kotlin

/**
 * @Description :枚举类test
 * @Auther tengfei.li
 * @Since 2022/8/5 9:52
 * @Version 1.0
 */
class EnumTest {
}

enum class Color{
    RED,BLACK,BLUE,GREEN,WHITE
}

enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    //自 Kotlin 1.1 起，可以使用 enumValues<T>() 和 enumValueOf<T>() 函数以泛型的方式访问枚举类中的常量 ：
    print(enumValues<T>().joinToString { it.name })
}

enum class Color2(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}


fun main(args: Array<String>) {
    //枚举类声明
    var color:Color=Color.BLUE
    println(Color.values())
    println(Color.valueOf("RED"))
    println(color.name)
    println(color.ordinal)

    printAllValues<RGB>() // 输出 RED, GREEN, BLUE

}