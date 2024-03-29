package com.tengfei.fairy.Kotlin

/**
 * @Description :扩展函数可以在已有类中添加新的方法，不会对原类做修改，
 *     扩展函数定义形式:
 *    fun receiverType.functionName(params){
 *                             body
 *              }
 *  receiverType：表示函数的接收者，也就是函数扩展的对象
 * functionName：扩展函数的名称
 * params：扩展函数的参数，可以为NULL
 *
 * @Auther tengfei.li
 * @Since 2022/8/4 9:27
 * @Version 1.0
 */
class User(var name:String)  //kotlin 类空实现，无需{}

/**扩展函数**/
fun User.Print(){
    print("用户名 $name")
}

// 扩展函数 swap,调换不同位置的值
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]     //  this 对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main(arg:Array<String>){

    var user = User("Runoob")
    user.Print()

    val l = mutableListOf(1, 2, 3)
    // 位置 0 和 2 的值做了互换
    l.swap(0, 2) // 'swap()' 函数内的 'this' 将指向 'l' 的值
    println()
    println(l.toString())
}