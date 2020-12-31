package com.kevin.entity;

import lombok.Data;

/**
 * @author kevin
 * @date 2019-11-15 13:28
 * @description todo
 **/
@Data
public class Address {
    private String provinces;
    private String city;
    private String area;


    @Override
    public String toString() {
        return "Address{" +
                "provinces='" + provinces + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
