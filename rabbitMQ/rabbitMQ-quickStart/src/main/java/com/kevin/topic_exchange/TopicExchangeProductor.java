package com.kevin.topic_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-7 16:58
 * @description todo
 **/
public class TopicExchangeProductor {
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

      //交换机
      String exchangeName="policeman.key";

      String routingKey1="policeman.key1";
      String routingKey2="policymaker.key2";
      String routingKey3="policymaker.key3";

      channel.basicPublish(exchangeName,routingKey1,null,"我是第一条消息".getBytes());
      channel.basicPublish(exchangeName,routingKey2,null,"我是第二条消息".getBytes());
      channel.basicPublish(exchangeName,routingKey3,null,"我是第三条消息".getBytes());
  }
}
