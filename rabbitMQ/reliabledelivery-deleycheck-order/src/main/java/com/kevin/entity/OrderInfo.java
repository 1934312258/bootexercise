package com.kevin.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author
 * @date 2019-11-12 22:18
 * @description todo
 **/
@Data
@ToString
public class OrderInfo {
    private long orderNo;

    private Date createTime;

    private Date updateTime;

    private String userName;

    private double money;

    private Integer productNo;
}
