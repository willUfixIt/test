/**
 * Project Name:springboot_hotel
 * File Name:Student.java
 * Package Name:cn.java.entity
 * Date:上午11:03:54
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.entity;

import java.io.Serializable;

/**
 * Description: QQ：1841670794，870599752(加好友时记得备注哦) Date: 上午11:03:54 <br/>
 * 
 * @author 丁鹏(大胆开车，幽默讲课)
 * @version
 * @see
 */
public class Student implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 726898403107260353L;

    private String stuName;// 学生名

    private String gender;// 性别

    private Integer age;// 学生年龄

    public Student() {

        super();
        // Auto-generated constructor stub

    }

    public Student(String stuName, String gender, Integer age) {
        super();
        this.stuName = stuName;
        this.gender = gender;
        this.age = age;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [stuName=" + stuName + ", gender=" + gender + ", age=" + age + "]";
    }

}
