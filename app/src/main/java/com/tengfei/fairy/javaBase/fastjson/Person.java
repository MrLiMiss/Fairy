package com.tengfei.fairy.javaBase.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @ Description :Person  Bean
 * @ Author 李腾飞
 * @ Time 2021/7/19   15:10
 * @ Version :
 */
public class Person {

    @JSONField(name="LAST NAME", ordinal = 2)
    private String lastName;

    @JSONField(name = "AGE", serialize=true)
    private int age;

    @JSONField(name ="SCHOOL",serialize = true)
    private String school;

    @JSONField(name="FIRST NAME", ordinal = 1)
    private String firstName;
    
    @JSONField(name = "FULL NAME",ordinal = 3)
    private String fullName;

    @JSONField(name = "DATE OF BIRTH",ordinal = 4,format="yyyy/MM/dd")
    private Date dateOfBirth;

    public Person(int age, String lastName,String firstName,String fullName, Date dateOfBirth,String school) {
        super();
        this.age = age;
        this.firstName=firstName;
        this.lastName=lastName;
        this.fullName= fullName;
        this.dateOfBirth = dateOfBirth;
        this.school=school;
    }


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // 标准 getters & setters


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
