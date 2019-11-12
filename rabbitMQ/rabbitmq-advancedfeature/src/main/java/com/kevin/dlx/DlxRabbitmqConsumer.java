package com.kevin.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 13:44
 * @description todo
 **/
public class DlxRabbitmqConsumer {
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

      //声明正常的队列
      String normalExchangeName="kevin.normal.exchange";
      String exchangeType="topic";
      String normalQueueName="kevin.normal.queue";
      String routingkey="kevin.dlx.#";
      channel.exchangeDeclare(normalExchangeName,exchangeType,true,false,null);

      //声明死信队列
      String dlxExchangeName="kevin.dlx.exchange";
      String dlxQueueName="kevin.dlx.queue";

      Map<String,Object>info=new HashMap<>();
      //正常队列上绑定死信队列
      info.put("x-dead-letter-exchange",dlxExchangeName);
      info.put("x-max-length",4);
      channel.queueDeclare(normalQueueName,true,false,false,info);
      channel.queueBind(normalQueueName,normalExchangeName,routingkey);



  }
}
