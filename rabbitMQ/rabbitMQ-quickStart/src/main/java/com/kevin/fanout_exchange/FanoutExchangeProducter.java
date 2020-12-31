package com.kevin.fanout_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-8 16:19
 * @description todo
 **/
public class FanoutExchangeProducter {
    public static void main(String[] args) throws IOException, TimeoutException {
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

        //定义routingKey   ,扇形交换机可以不用routingKey
        String routingKey = "";

        //消息体内容
        String messageBody = "kevin handsome";

        for (int i = 0; i < 1000; ++i) {
            channel.basicPublish(exchangeName, "", null, (messageBody + i).getBytes());
        }


    }
}
