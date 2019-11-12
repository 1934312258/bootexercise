package com.kevin.ttl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 14:13
 * @description todo
 **/
public class TtlRabbitmqProducer {
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

      String exchangeName="kevin.ttl.direct";
      String routingkey="kevin.ttl.key";
      String queueName="kevin.ttl.queue";

      channel.exchangeDeclare(exchangeName,"direct",true,false,null);

      Map<String,Object>info=new HashMap<>();
      //设置队列中的消息10s没被消费就会过期
      info.put("x-message-ttl",10000);
      //队列长度
      info.put("x-max-length",4);
      channel.queueDeclare(queueName,true,false,false,info);

      //绑定
      channel.queueBind(queueName,exchangeName,routingkey);

      String message="hello kevin";
      for(int i=0;i<10;++i){
          channel.basicPublish(exchangeName,routingkey,null,(message+i).getBytes());
      }
  }
}
