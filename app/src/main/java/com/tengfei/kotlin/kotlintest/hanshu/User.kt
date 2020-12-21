package com.tengfei.kotlin.kotlintest.hanshu

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020/12/21   16:11
 * @ Version :
 */
class User constructor(name: String) {
    var name:String?=null
    var age:Int?=null
    var phoneNum:String?=null
    //次构造函数.如果类有主构造函数，每个次构造函数都要，或直接或间接通过另一个次构造函数代理主构造函数。在同一个类中代理另一个构造函数使用 this 关键字：
    constructor(name:String,age:Int,phoneNum:String):this(name){
        this.name=name
        this.age=age
        this.phoneNum=phoneNum

    }

}