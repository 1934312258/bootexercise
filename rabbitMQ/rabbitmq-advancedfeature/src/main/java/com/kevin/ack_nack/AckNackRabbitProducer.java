package com.kevin.ack_nack;

import com.kevin.confirm.KevinConfirmListener;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-10 13:10
 * @description todo
 **/
public class AckNackRabbitProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.248.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setConnectionTimeout(100000);

        //创建连接
        Connection connection = factory.newConnection();
        //创建一个channel
        Channel channel = connection.createChannel();

        String exchangeName = "kevin.policeman";
        String routingkey = "kevin.policeman.key";
        String message = "hello kevin";
        //消息确认监听
        channel.addConfirmListener(new KevinConfirmListener());

        for (int i = 0; i < 10; ++i) {
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("mark", i);

            AMQP.BasicProperties properties = new BasicProperties().builder()
                    .deliveryMode(2)//消息持久化
                    .contentEncoding("utf-8")
                    .correlationId(UUID.randomUUID().toString())
                    .headers(infoMap)
                    .build();

            channel.basicPublish(exchangeName, routingkey, properties, (message + i).getBytes());
        }


    }
}
