package com.kevin.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author kevin
 * @date 2019-11-18 9:28
 * @description todo
 **/
@Slf4j
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("correlationData:=========>{},ack的标志{}",correlationData.getId(),ack);
        if(ack){
            log.info("mq生产端消息已经成功投递到了broker，更新我们的消息日志表");
        }else{
            log.warn("mq生产端没有被broker ack:{}",cause);
        }

    }
}
