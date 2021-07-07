package com.tengfei.fairy.javaBase.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2021/7/7   10:35
 * @ Version :
 */
public class User implements Serializable {
    private boolean isDeveloper;

    //反序列化时转化成json对象时会把name 转化成fullName
    @SerializedName("fullName")
    private String name;

    //json 传email/QQEmail   都映射到email，如果json中同时有 value alternate则谁后赋值用谁的覆盖
    @SerializedName(value = "email",alternate = "QQEmail")
    private String email;

    @Expose(serialize = true, deserialize = true)   //@Expose 注解需将所有参数注解，否则新自定义忽略@@Expose  GSON对象会忽略位@Expose注解的参数
    private int age;

    //serialize 是否进行序列换   deserialize是否进行反序列化
    @Expose(serialize = false, deserialize = false)
    private String company;

    @Expose(serialize = true, deserialize = true) //新自定义忽略@Expose  GSON对象会忽略位@Expose注解的参数(需对内部类也进行@@Expose 注解)
    private UserAddress userAddress;

    public User(boolean b, String myName, int myAge, String myEmail,String company, UserAddress myUsrAdress) {
        this.isDeveloper = b;
        this.name = myName;
        this.age = myAge;
        this.email = myEmail;
        this.company=company;
        userAddress = myUsrAdress;
    }

    public static class UserAddress {
        private String city;
        private String country;
        private String houseNumber;
        private String street;

        public UserAddress(String city, String country, String houseNumber, String street) {
            this.city = city;
            this.country = country;
            this.houseNumber = houseNumber;
            this.street = street;
        }


    }

    public  class UserAddress2 {
        private String city;
        private String country;
        private String houseNumber;
        private String street;

        public UserAddress2(String city, String country, String houseNumber, String street) {
            this.city = city;
            this.country = country;
            this.houseNumber = houseNumber;
            this.street = street;
        }


    }


}
