package com.tengfei.fairy.test.Serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @ Description :
 * 如果一个可序列化的类的成员不是基本类型，也不是String类型，那这个引用类型也必须是可序列化的，故person必须序列化；否则，会导致此类不能序列化。
 * @ Author 李腾飞
 * @ Time 2021/2/24   18:01
 * @ Version :
 */
public class Teacher implements Serializable {

    private String name;
    private Person person;

    public Teacher(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }

    public static void main(String[] args) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
            Person person = new Person("路飞", 20);
            Teacher teacher = new Teacher("雷利", person);
            oos.writeObject(teacher);
        }
    }
}