package com.kevin.javaDemo.lambda;

import java.util.Date;

/**
 * @author kevin
 * @date 2020-7-2 16:30
 * @description todo
 **/
public class People {
    Integer age;
    String name;
    String id;
    Date brith;

    public People(String id, Integer age, String name, Date brith) {
        this.age = age;
        this.brith = brith;
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }
}
