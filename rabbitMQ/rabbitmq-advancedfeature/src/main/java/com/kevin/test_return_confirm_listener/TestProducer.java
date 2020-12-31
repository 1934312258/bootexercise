package com.kevin.test_return_confirm_listener;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 15:53
 * @description todo
 **/
public class TestProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.159.8");
        factory.setPort(5672);
        factory.setVirtualHost("kevin");
        factory.setUsername("kevin");
        factory.setPassword("kevin");
        factory.setConnectionTimeout(100000);

        //创建连接
        Connection connection = factory.newConnection();
        //创建一个channel
        Channel channel = connection.createChannel();

        String exchange = "test_return_exchange";
        String routingkey = "return.save";
        String routingkeyerror = "erroe.save";

        String message = "hello rabbitmq return message";

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, BasicProperties properties, byte[] body) throws IOException {
                System.err.println("---------handle  return----------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });
        channel.basicPublish(exchange, routingkeyerror, true, null, message.getBytes());
    }
}
