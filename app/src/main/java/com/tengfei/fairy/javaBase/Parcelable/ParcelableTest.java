package com.tengfei.fairy.javaBase.Parcelable;

/**
 * @ Description : Parcelable 序列化 demo
 * @ Author 李腾飞
 * @ Time 12/17/21   5:28 PM
 * @ Version :
 */
public class ParcelableTest {
    /**
     * Parcelable和Serializable都是实现序列化并且都可以用于Intent间传递数据,Serializable是Java的实现方式,可能会频繁的IO操作,所以消耗比较大,但是实现方式简单 Parcelable是Android提供的方式,效率比较高,但是实现起来复杂一些 , 二者的选取规则是:内存序列化上选择Parcelable, 存储到设备或者网络传输上选择Serializable(当然Parcelable也可以但是稍显复杂)
     *
     * 选择序列化方法的原则
     *
     * 1）在使用内存的时候，Parcelable比Serializable性能高，所以推荐使用Parcelable。
     *
     * 2）Serializable在序列化的时候会产生大量的临时变量，从而引起频繁的GC。
     *
     * 3）Parcelable不能使用在要将数据存储在磁盘上的情况，因为Parcelable不能很好的保证数据的持续性在外界有变化的情况下。尽管Serializable效率低点，但此时还是建议使用Serializable 。
     */

    /**
     * Parcelable和Serializable的作用、效率、区别及选择。
     *
     * 1、作用
     * Serializable的作用是为了保存对象的属性到本地文件、数据库、网络流、rmi以方便数据传输，当然这种传输可以是程序内的也可以是两个程序间的。而Android的Parcelable的设计初衷是因为Serializable效率过慢，为了在程序内不同组件间以及不同Android程序间(AIDL)高效的传输数据而设计，这些数据仅在内存中存在，Parcelable是通过IBinder通信的消息的载体。
     *
     * 从上面的设计上我们就可以看出优劣了。
     *
     * 2、效率及选择
     * Parcelable的性能比Serializable好，在内存开销方面较小，所以在内存间数据传输时推荐使用Parcelable，如activity间传输数据，而Serializable可将数据持久化方便保存，所以在需要保存或网络传输数据时选择Serializable，因为android不同版本Parcelable可能不同，所以不推荐使用Parcelable进行数据持久化。
     *
     * 3、编程实现
     * 对于Serializable，类只需要实现Serializable接口，并提供一个序列化版本id(serialVersionUID)即可。Parcelable则需要实现writeToParcel、describeContents函数以及静态的CREATOR变量，实际上就是将如何打包和解包的工作自己来定义，而序列化的这些操作完全由底层实现。
     *
     * 4、高级功能上
     * Serializable序列化不保存静态变量，可以使用Transient关键字对部分字段不进行序列化，也可以覆盖writeObject、readObject方法以实现序列化过程自定义。
     */
     public static void main(String [] args){

     }

}
