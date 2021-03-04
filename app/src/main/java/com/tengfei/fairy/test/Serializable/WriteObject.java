package com.tengfei.fairy.test.Serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @ Description :测试序列化
 * @ Author 李腾飞
 * @ Time 2021/2/24   17:53
 * @ Version :
 */
public class WriteObject {
    public static void main(String[] args) {
        try (//创建一个ObjectOutputStream输出流
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            //将对象序列化到文件s
            Person person = new Person("9龙", 23);
            oos.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
