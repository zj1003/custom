package com.zhaojun.entity;

/**
 * @author ZhaoJun
 * @date 2019/7/12 11:31
 */

public class User {

    private String name;
    private String address;
    private Integer age;

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", address='" + address + '\'' + ", age=" + age + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
