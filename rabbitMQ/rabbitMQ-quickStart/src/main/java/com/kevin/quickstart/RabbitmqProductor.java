package com.kevin.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-7 14:34
 * @description todo
 **/
public class RabbitmqProductor {
  public static void main(String[] args) throws IOException, TimeoutException {
    //创建连接工厂
    ConnectionFactory factory=new ConnectionFactory();
    //设置工厂属性
    factory.setHost("192.168.101.19");
    factory.setPort(5672);
    factory.setVirtualHost("kevin");
    factory.setUsername("kevin");
    factory.setPassword("kevin");

    //通过连接工厂创建连接对象
    Connection connection=factory.newConnection();
    //通过连接创建channel
    Channel channel=connection.createChannel();
    //通过channel发送消息
    for(int i=0;i<5;i++){
        String message="hello--"+i;
        /**
         * 老师以前讲过说我们的消息会发送的exchange上，
         * 但是在这里我们没有指定交换机?那我们的消息发送到哪里了？？？？
         * The default exchange is implicitly bound to every queue, with a routing key equal to the queue name.
         * It is not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
         * 说明:加入我们消息发送的时候没有指定具体的交换机的话，那么就会发送到rabbimtq指定默认的交换机上，
         * 那么该交换机就会去根据routing_key 查找对应的queueName 然后发送的该队列上.
         *
         *第一个参数是交换机exchange，第二个参数是routingKey*/
        channel.basicPublish("","kevin-queue-01",null,message.getBytes());
    }
    channel.close();
    connection.close();
  }
}
