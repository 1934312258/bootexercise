package com.kevin.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-10 11:51
 * @description todo
 **/
public class MessageConsumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置工厂属性
        factory.setHost("192.168.101.19");
        factory.setPort(5672);
        factory.setVirtualHost("kevin");
        factory.setUsername("kevin");
        factory.setPassword("kevin");

        //通过连接工厂创建连接对象
        Connection connection = factory.newConnection();
        //通过连接创建channel
        Channel channel = connection.createChannel();

        String exchangeName = "policeman.key";
        String exchangeType = "direct";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

        String queueName = "kevin.directqueue";
        String routingkey = "kevin.directqueue.key";
        channel.queueDeclare(queueName, true, true, false, null);

        channel.queueBind(queueName, exchangeName, routingkey);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);


        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("encoding:" + delivery.getProperties().getContentEncoding());
            System.out.println("company:" + delivery.getProperties().getHeaders().get("company"));
            System.out.println("correlationId" + delivery.getProperties().getCorrelationId());
        }
    }
}
