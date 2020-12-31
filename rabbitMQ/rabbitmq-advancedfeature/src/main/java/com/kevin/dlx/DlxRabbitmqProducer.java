package com.kevin.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 13:37
 * @description todo
 **/
public class DlxRabbitmqProducer {
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

        AMQP.BasicProperties properties = new BasicProperties().builder()
                .deliveryMode(2)
                .expiration("10000")
                .build();

        //声明正常的队列
        String normalExchangeName = "kevin.normal.exchange";
        String routingkey = "kevin.dlx.key";

        String message = "我是测试的死信消息";
        for (int i = 0; i < 100; ++i) {
            channel.basicPublish(normalExchangeName, routingkey, properties, message.getBytes());
        }
    }
}
