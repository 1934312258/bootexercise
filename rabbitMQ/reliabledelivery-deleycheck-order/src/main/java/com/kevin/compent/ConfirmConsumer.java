package com.kevin.compent;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-10 21:48
 * @description todo
 **/
public class ConfirmConsumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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

        //声明交换机
        String exchangeName = "kevin.confirm";
        String exchangeType = "topic";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

        String queueName = "kevin.confirm.queue";
        channel.queueDeclare(queueName, true, false, false, null);

        String routingkey = "kevin.confirm.#";
        channel.queueBind(queueName, exchangeName, routingkey);

        channel.basicConsume(queueName, false, new AckConsumer(channel));
    }
}
