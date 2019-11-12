package com.kevin.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpAdmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-8 16:59
 * @description todo
 **/
public class MessageProductor {
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

      //创建消息参数
      Map<String,Object>headsMap=new HashMap<>();
      headsMap.put("company","technology");
      headsMap.put("name","kevin");

      AMQP.BasicProperties properties=new BasicProperties().builder()
              .deliveryMode(2) //2标识持久化消息，1标识服务重启后消息不会被持久化
              .expiration("10000") //消息过期时间10s
              .contentEncoding("utf-8")
              .correlationId(UUID.randomUUID().toString())//判断消息队列和回调队列，方便RPC响应与请求关联,通过其确定回调队列返回的信息是哪条消息的回复
              .headers(headsMap)
              .build();

      for(int i=0;i<5;i++){
          String message="hello--"+i;
          channel.basicPublish("kevin.policeman","kevin.policeman.key",properties,message.getBytes());

      }
      channel.close();
      connection.close();


  }
}
