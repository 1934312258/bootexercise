package com.kevin.customer_consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 11:31
 * @description todo
 **/
public class KevinProducer {
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

      String exchangeName="kevin.policeman";
      String routingkey="kevin.policeman.key";
      String message="hello kevin";
      for (int i=0;i<10;++i){
          channel.basicPublish(exchangeName,routingkey,null,(message+i).getBytes());
      }
  }
}
