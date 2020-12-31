package com.kevin;

public enum MyDay {

    MONDAY(1, "星期一"), THUSDAY(2, "星期二");//这个后面必须有分号

    private int code;
    private String name;

    private MyDay(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

}