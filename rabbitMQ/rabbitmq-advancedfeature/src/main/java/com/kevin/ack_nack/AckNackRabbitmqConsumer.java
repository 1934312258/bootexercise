package com.kevin.ack_nack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-10 13:22
 * @description todo
 **/
public class AckNackRabbitmqConsumer {
  public static void main(String[] args) throws IOException, TimeoutException {
      //创建连接工厂
      ConnectionFactory factory=new ConnectionFactory();
      factory.setHost("192.168.159.8");
      factory.setPort(5672);
      factory.setVirtualHost("kevin");
      factory.setUsername("kevin");
      factory.setPassword("kevin");
      factory.setConnectionTimeout(100000);

      //创建连接
      Connection connection=factory.newConnection();
      //创建一个channel
      Channel channel=connection.createChannel();

      //声明交换机
      String exchangeName="kevin.policeman";
      String exchangeType="direct";
      channel.exchangeDeclare(exchangeName,exchangeType,true,false,null);

      String queueName="kevin.ack.queue";
      channel.queueDeclare(queueName,true,true,false,null);

      String rountingkey="kevin.ack,key";
      channel.queueBind(queueName,exchangeName,rountingkey);

      //消费端限流，需要关闭消息自动签收
      channel.basicConsume(queueName,false,new AckConsumer(channel));

  }
}
