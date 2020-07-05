package com.kevin.javaDemo.lambda;

import java.util.Date;

/**
 * @author kevin
 * @date 2020-7-2 16:30
 * @description todo
 **/
public class People {
    int age;
    String name;
    String id;
    Date brith;
    public People(String id,int age,Date brith){
        this.age=age;
        this.brith=brith;
        this.id=id;
    }

    public People getThis(){
        return this;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
