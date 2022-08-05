package com.tengfei.fairy.Kotlin

/**
 * @Description :扩展属性
扩展属性有以下原则：
1.扩展属性不能有初始值（没有存储属性值的幕后宇段）
2.不能用 field 关键字显式访问幕后字段。
3.扩展只读属性必须提供 getter 方法， 扩展读写属性必须提供 getter、setter 方法。
示例代码：
 * @Auther tengfei.li
 * @Since 2022/8/4 11:02
 * @Version 1.0
 */
class PropertyExtend (var firstName:String,var lastName:String){
}

/**---------------------伴生对象扩展-------------------------------*/
class MyClass {
    //相当于Myclass的内部静态单例对象
    companion object { }  // 将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    println("伴随对象的扩展函数")
}

val MyClass.Companion.no: Int
    get() = 10
/**---------------------伴生对象扩展-------------------------------*/




/**---------------------扩展函数-------------------------------*/
class A {
    fun bar() = println("A类的bar方法")
}

class B {
    fun baz() = println("B类的baz方法")
    fun A.foo() {
        this.bar()//this指的是被扩展类。
        this@B.baz()//this可以省略
    }

    fun test(target: A) {
        target.bar()//调用目标类的成员方法
        target.foo()//调用目标类的扩展方法
    }
}
/**---------------------扩展函数-------------------------------*/



/**---------------------扩展属性------------------------------*/
//为User 扩展读写属性
var PropertyExtend.fullName: String
    get() = "${firstName}.${lastName}"
    set(value) {
        println("执行扩展属性 fullName setter 方法")
        //value 字符串中不包含．或包含几个 都不行
        if ("." !in value || value.indexOf(".") != value.lastIndexOf(".")) {
            println("您输入的 fullName 不合法")
        } else {
            var tokens = value.split("·")
            firstName = tokens[0]
            lastName = tokens[1]
        }
    }
/**---------------------扩展属性------------------------------*/

/**---------------------扩展作用域------------------------------*/

/**---------------------扩展作用域------------------------------*/





/**---------------------扩展声明为成员------------------------------*/

/**
 *
在一个类内部你可以为另一个类声明扩展。
在这个扩展中，有个多个隐含的接受者，其中扩展方法定义所在类的实例称为分发接受者，而扩展方法的目标类型的实例称为扩展接受者。
 */
class D {
    fun bar() { println("D bar") }
}

class C {
    fun baz() { println("C baz") }

    //为D扩展foo（）方法
    fun D.foo() {
        bar()   // 调用 D.bar
        baz()   // 调用 C.baz
    }

    fun caller(d: D) {
        d.foo()   // 调用扩展函数
    }
}

/**---------------------扩展声明为成员------------------------------*/


fun main(args: Array<String>) {

    //1.伴生对象扩展
    println("no:${MyClass.no}")
    MyClass.foo()

    //2.如果扩展类和扩展方法所在类中的方法签名一致，那么如果不加this@类名指定调用哪个类的方法时，默认调用的是被扩展类的同名方法
    var b = B()
    b.test(A())

    //3.扩展属性
    var wangdachui =PropertyExtend("大锤","王")
    println(wangdachui.fullName)

    //4.一个类内部为另外一个类声明扩展
    println("一个类内为另一个类类扩展方法")
    val c: C = C()
    val d: D = D()
    c.caller(d)

}
