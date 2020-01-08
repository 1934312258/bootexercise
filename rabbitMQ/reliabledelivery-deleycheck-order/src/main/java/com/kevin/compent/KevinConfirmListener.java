package com.kevin.compent;

import com.rabbitmq.client.ConfirmListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-10 21:25
 * @description todo
 **/
public class KevinConfirmListener implements ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
          System.out.println("签收成功");
        }
        System.out.println(ack);
    }
}
