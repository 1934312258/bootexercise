package com.kevin.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author kevin
 * @date 2019-11-18 9:36
 * @description todo
 **/
@Slf4j
public class ReturnCallBack implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        ObjectMapper mapper = new ObjectMapper();
        log.warn("correlationId:{}", message.getMessageProperties().getCorrelationId());
        log.warn("replyTest:{}", replyText);
        log.warn("replayCode{}", replyCode);
        log.warn("交换机：{}", exchange);
        log.warn("routingKey:{}", routingKey);
        log.info("需要更新数据库日日志表的消息记录为不可达");
    }
}
