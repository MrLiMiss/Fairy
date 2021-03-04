package com.tengfei.fairy.javaBase.Serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @ Description :输入流序列换
 * @ Author 李腾飞
 * @ Time 2021/2/24   17:59
 * @ Version :
 */
public class ReadObject {
    public static void main(String[] args) {
        try (//创建一个ObjectInputStream输入流
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"))) {
            Person brady = (Person) ois.readObject();
            System.out.println(brady);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
