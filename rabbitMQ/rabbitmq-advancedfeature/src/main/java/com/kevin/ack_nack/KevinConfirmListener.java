package com.kevin.ack_nack;

import com.rabbitmq.client.ConfirmListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-10 21:25
 * @description todo
 **/
public class KevinConfirmListener implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack) {
            System.out.println("签收成功");
        }
        System.out.println(ack);
    }
}
