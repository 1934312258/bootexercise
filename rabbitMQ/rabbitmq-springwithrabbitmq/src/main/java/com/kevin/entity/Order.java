package com.kevin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-15 13:30
 * @description todo
 **/
@Data
public class Order {
    private String orderNo;
    private Date createDate;
    private double payMoney;
    private String userName;

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", createDt=" + createDate +
                ", payMoney=" + payMoney +
                ", userName='" + userName + '\'' +
                '}';
    }
}
