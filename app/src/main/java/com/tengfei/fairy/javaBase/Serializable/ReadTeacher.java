package com.tengfei.fairy.javaBase.Serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @ Description :反序列化的顺序与序列化时的顺序一致。
 * @ Author 李腾飞
 * @ Time 2021/2/24   18:04
 * @ Version :
 */
public class ReadTeacher {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))) {
            Teacher t1 = (Teacher) ois.readObject();
            Teacher t2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher t3 = (Teacher) ois.readObject();
            System.out.println(t1 == t2);
            System.out.println(t1.getPerson() == p);
            System.out.println(t2.getPerson() == p);
            System.out.println(t2 == t3);
            System.out.println(t1.getPerson() == t2.getPerson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
