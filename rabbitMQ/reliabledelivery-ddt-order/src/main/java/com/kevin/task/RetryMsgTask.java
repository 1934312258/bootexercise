package com.kevin.task;

import com.kevin.bo.MessageBo;
import com.kevin.compent.MsgSender;
import com.kevin.constants.MqConst;
import com.kevin.entity.MessageContent;
import com.kevin.enumration.MsgstatusEnum;
import com.kevin.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kevin
 * @date 2019-11-13 14:39
 * @description todo
 **/
@Component
@Slf4j
public class RetryMsgTask {

    @Autowired
    private MsgSender sender;

    @Autowired
    private MsgContentMapper mapper;

    //延时5s启动，周期10s一次
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void retrySend() {
        System.out.println("-----------------------");
        //查询五分钟消息状态还没有完结的消息
        List<MessageContent> contents = mapper.queryMsgs(MsgstatusEnum.CONSUMER_SUCCESS.getCode(), MqConst.TIME_DIFF);
        for (MessageContent content : contents) {
            if (content.getMaxRetry() > content.getCurrentRetry()) {
                MessageBo message = new MessageBo();
                message.setMsgId(content.getMsgId());
                message.setProductNo(content.getProductNo());
                message.setOrderNo(content.getOrderNo());
                //更新消息重试次数
                mapper.updateMsgRetryCount(message.getMsgId());
                sender.sendMsg(message);
            } else {
                log.warn("消息：{}已达到最大的重试次数", content);
            }
        }
    }


}
