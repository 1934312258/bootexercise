package com.kevin.topic_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-8 15:50
 * @description todo
 **/
public class TopicExchangeConsumer {
  public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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
      String exchangeType="topic";
      //是否持久化，是否自动删除
      channel.exchangeDeclare(exchangeName,exchangeType,true,true,null);

      //声明队列
      String queueName="policeman.queue";
      //是否持久化，是否独占，是否自动删除
      channel.queueDeclare(queueName,true,false,false,null);

      //声明绑定关系#可以多层级绑定，*只能绑定一层
      String bingdingStr="policyman.#";
      channel.queueBind(queueName,exchangeName,bingdingStr);
      //声明消费者
      QueueingConsumer consumer=new QueueingConsumer(channel);

      //开始消费,从服务端拉取消息
      channel.basicConsume(queueName,true,consumer);
      while (true){
          QueueingConsumer.Delivery delivery=consumer.nextDelivery();
      System.out.println("接受到消息"+new String(delivery.getBody()));
      }
  }
}
