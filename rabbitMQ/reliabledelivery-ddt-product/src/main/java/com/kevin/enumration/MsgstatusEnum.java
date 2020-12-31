package com.kevin.enumration;

import lombok.Getter;

/**
 * @author kevin
 * @date 2019-11-12 10:38
 * @description todo
 **/
@Getter
public enum MsgstatusEnum {

    SENDING(0, "发送中"),
    SENDING_SUCCESS(1, "消息发送成功"),
    SENDING_FALL(2, "消息发送失败"),
    CONSUMER_SUCCESS(3, "消费成功"),
    CONSUMER_FALL(4, "消费失败");


    private Integer code;

    private String msgStatus;

    MsgstatusEnum(Integer code, String msgStatus) {
        this.code = code;
        this.msgStatus = msgStatus;
    }

}
