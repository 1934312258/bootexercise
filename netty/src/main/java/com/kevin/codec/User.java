package com.kevin.codec;

/**
 * @author kevin
 * @date 2019-12-22 12:34
 * @description todo
 **/
public class User {
    public User(int id ,String name ){
        this.id=id;
        this.name=name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

}
