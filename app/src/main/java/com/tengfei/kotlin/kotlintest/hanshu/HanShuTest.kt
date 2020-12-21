package com.tengfei.kotlin.kotlintest.hanshu

/**
 * @ Description :   Kotlin(let,with,run,apply,also)函数
 * @ Author 李腾飞
 * @ Time 2020/12/21   15:16
 * @ Version :
 */

fun main(args: Array<String>) {
    /**
     *  1、let函数,let扩展函数的实际上是一个作用域函数，当你需要去定义一个变量在一个特定的作用域范围内，let函数的是一个不错的选择；
     *  let函数另一个作用就是可以避免写一些判断null的操作。
     *  在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式。
     */
    var hanShuTest = HanShuTest()
    println("-------------------------测试let——————————————————————————————")
    val testLet = hanShuTest.testLet()

    /**
     * 2、with函数
     * with函数不是以扩展的形式存在的。它是将某对象作为函数的参数，
     * 在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。
     */
    println("-------------------------测试with——————————————————————————————")
    val testWith = hanShuTest.testWith()

    /**
     * 3、run函数
     * run函数实际上可以说是let和with两个函数的结合体，run函数只接收一个lambda函数为参数，
     * 以闭包形式返回，返回值为最后一行的值或者指定的return的表达式。
     *
     * run使用场景： 适用于let,with函数任何场景。因为run函数是let,with两个函数结合体，
     * 准确来说它弥补了let函数在函数体内必须使用it参数替代对象，
     * 在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，
     * 另一方面它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理
     */
    println("-------------------------测试run——————————————————————————————")
    var testRun = hanShuTest.testRun()

    /**
     * 4、apply函数
     * 从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，
     * run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身。
     * apply使用场景：
     * apply一般用于一个对象实例初始化的时候，需要对对象中的属性进行赋值。
     * 或者动态inflate出一个XML的View的时候需要给View绑定数据也会用到，这种情景非常常见。
     * 特别是在我们开发中会有一些数据model向View model转化实例化的过程中需要用到。
     */
    println("-------------------------测试apply——————————————————————————————")
    var testApply = hanShuTest.testApply()

    /**
     * 5、also函数
     * let是以闭包的形式返回，返回函数体内最后一行的值，如果最后一行为空就返回一个Unit类型的默认值。
     * 而also函数返回的则是传入对象的本身
     */
    println("-------------------------测试also——————————————————————————————")
    var testAlso=hanShuTest.alsoTest()


}

class HanShuTest {

    fun testLet() {
        // fun <T, R> T.let(f: (T) -> R): R { f(this)}
        val result = "testLet".let {
            println(it.length)
            1000
            2000
        }
        println(result)
    }

    fun testWith() {
        val user = User("Kotlin", 1, "1111111")
        val result = with(user, {
            println("my name is $name, I am $age years old, my phone number is $phoneNum")
            1000
            2000
        })
        println(result)
    }

    fun testRun() {
        val user = User("Kotlin", 1, "1111111")
        //run函数可以完成非空判断
        val result = user?.run {
            println("my name is $name, I am $age years old, my phone number is $phoneNum")
            1000
            30000
        }
        println("result: $result")
    }

    fun testApply() {
        val user = User("Kotlin", 1, "1111111")
        //apply函数可以完成非空判断
        val result = user?.apply {
            println("my name is $name, I am $age years old, my phone number is $phoneNum")
            1000
        }
        //apply返回的是传入对象本身（此处为 user对象）
        println("result: $result")

    }

    fun alsoTest(){
        val user = User("Kotlin-also", 1, "1111111")
        //返回对象本身
        val result = "testLet"?.also {
            println(it.length)
            1000
        }
        val result1=user.also {
           println(it.name)
        }
        println("result:$result")
        println("result1:$result1")
    }

}

