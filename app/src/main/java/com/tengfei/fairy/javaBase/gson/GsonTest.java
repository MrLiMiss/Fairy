package com.tengfei.fairy.javaBase.gson;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2021/7/7   09:47
 * @ Version :
 */
public class GsonTest {
    public static String TAG = GsonTest.class.getSimpleName();

    public static void main(String[] args) {
        test();

    }

    public static void test() {
        String json2 = "[\"apple\", \"pear\", \"banana\"]";
        Gson gson2 = new Gson();
        // 传入的java类型是String[].class
        String[] fruits = gson2.fromJson(json2, String[].class);
        System.out.println(fruits[0]);
        User.UserAddress userAddress = new User.UserAddress("beijing", "china", "68", "guangqustreet");

        User user1 = new User(true, "wangxue", 27, "152312414@qq.com", "huawei",userAddress);
        Gson gson = new Gson();
        String userString = gson.toJson(user1, User.class);
        System.out.println(userString + fruits.toString());

        String user2String="{\"isDeveloper\":true,\"fullName\":\"反序列化2\",\"age\":27,\"email\":\"152312414@qq.com\",\"company\":\"华为\",\"userAddress\":{\"city\":\"beijing\",\"country\":\"china\",\"houseNumber\":\"68\",\"street\":\"guangqustreet\"}}";
        String user3String="{\"isDeveloper\":true,\"fullName\":\"反序列化3\",\"age\":27,\"QQEmail\":\"152312414@qq.com\",\"email\":\"12345678@qq.com\",\"company\":\"华为\",\"userAddress\":{\"city\":\"beijing\",\"country\":\"china\",\"houseNumber\":\"68\",\"street\":\"guangqustreet\"}}";

        //反序列化： 将字节序列转化为java对象
        User user2=gson.fromJson(user2String,User.class);
        User user3=gson.fromJson(user3String,User.class);



        //使用@Expose 注解是否序列化 反序列化，不能使用默认的Gson对象，新建Gson对象.未使用@Expose修饰的属性 在反序列化时 会被忽视
        Gson gsonNew =new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //序列化： 将java对象转换为字节序列
        User user = new User(true, "序列化", 27, "152312414@qq.com", "huawei",userAddress);
        String userSerialize = gsonNew.toJson(user, User.class);
        System.out.println("userSerialize:"+userSerialize);
    }
}
