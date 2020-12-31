package com.kevin.return_listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 14:58
 * @description todo
 **/
public class ReturnListenerConsumer {
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

        String exchangeName = "kevin.return.direct";
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);

        String queueName = "kevin.return.queue";
        channel.queueDeclare(queueName, true, false, false, null);

        String routingkey = "kevin.return.key.ok";
        channel.queueBind(queueName, exchangeName, routingkey);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

    }
}
