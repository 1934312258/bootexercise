package com.kevin.fanout_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-8 16:44
 * @description todo
 **/
public class FanoutExchangeConsumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.159.8");
        factory.setPort(5672);
        factory.setVirtualHost("kevin");
        factory.setUsername("kevin");
        factory.setPassword("kevin");

        //创建连接
        Connection connection = factory.newConnection();
        //创建一个channel
        Channel channel = connection.createChannel();

        //定义交换机名称
        String exchangeName = "policeman.key";
        String exchangeType = "fanout";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

        //声明队列
        String queueName = "kevin.fanout.queue";
        channel.queueDeclare(queueName, true, false, false, null);

        //声明绑定关系
        String bingdingStr = "";
        channel.queueBind(queueName, exchangeName, bingdingStr);

        QueueingConsumer consumer = new QueueingConsumer(channel);


        //开始消费
        channel.basicConsume(queueName, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println("接收到消息：" + new String(delivery.getBody()));
        }
    }
}
