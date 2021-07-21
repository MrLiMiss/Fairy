package com.tengfei.fairy.javaBase.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @ Description :阿里巴巴 fastJson 用法test
 * @ Author 李腾飞
 * @ Time 2021/7/19   14:57
 * @ Version :
 */
public class FastJsonTest {

    public static void main(String[] args) {
        test();
        testToJson();
    }


    public static void test(){
        List<Person> listPerson =new ArrayList<Person>();
        listPerson.add(new Person(15,"Doe","john","john Doe",new Date(),"Peking University"));
        listPerson.add(new Person(20,"沐沐","李","李沐沐",new Date(),"Tsinghua University"));
        //bean 转化为JSON
        String jsonOutPut1= JSON.toJSONString(listPerson);
        System.out.println("FastJsonTest-jsonOutPut1:"+jsonOutPut1);
        //BeanToArray 序列化功能（json对象转化为数组格式）
        String jsonOutput2= JSON.toJSONString(listPerson, SerializerFeature.BeanToArray);
        System.out.println("FastJsonTest-jsonOutPut2:"+jsonOutput2);

    }


    public static void testToJson(){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL NAME", "Doe " + i);
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }
        String jsonOutput = jsonArray.toJSONString();
        //输出jsonArray
        System.out.println("FastJsonTest-testToJson:"+jsonOutput);
    }

}
