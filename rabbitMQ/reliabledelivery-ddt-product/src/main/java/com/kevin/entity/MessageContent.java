package com.kevin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-12 10:26
 * @description todo
 **/
@Data
public class MessageContent {
    private String msgId;

    private long orderNo;

    private Date createTime;

    private Date updateTime;

    private Integer msgStatus;

    private String exchange;

    private String routingKey;

    private String errorCause;

    private Integer maxRetry;

    private Integer currentRetry=0;

    private Integer productNo;

}
