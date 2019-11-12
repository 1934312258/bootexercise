package com.kevin.return_listener;

import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-11 14:30
 * @description todo
 **/
public class ReturnListener implements com.rabbitmq.client.ReturnListener {
    @Override
    public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, BasicProperties properties, byte[] body) throws IOException {
        System.out.println("replyCode:"+replyCode);
        System.out.println("replyText:"+replyText);
        System.out.println("exchange:"+exchange);
        System.out.println("routingKey:"+routingKey);
        System.out.println("properties:"+properties);
        System.out.println("msg body:"+new String(body));
    }
}
