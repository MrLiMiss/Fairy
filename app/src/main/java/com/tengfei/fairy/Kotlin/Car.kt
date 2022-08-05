package com.tengfei.fairy.Kotlin

/**
 * @Description :
 * 扩展函数是静态解析的，并不是接收者类型的虚拟成员，
 * 在调用扩展函数时，具体被调用的的是哪一个函数，由调用函数的的对象表达式来决定的，
 * 而不是动态的类型决定的:
 * @Auther tengfei.li
 * @Since 2022/8/4 10:04
 * @Version 1.0
 */
open class Car()

class BigCar:Car()

fun Car.foo()="car"

fun BigCar.foo()="BigCar"

fun  printFoo(car:Car){
    println(car.foo())
}

fun main(arg:Array<String>){
    printFoo(BigCar())
}