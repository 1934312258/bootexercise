package com.kevin.bo;

import lombok.Data;

/**
 * @author kevin
 * @date 2019-11-11 16:50
 * @description 消息文本对象
 **/
@Data
public class MessageBo {
    private long orderNo;
    private long productNo;
    private String msgId;
}
