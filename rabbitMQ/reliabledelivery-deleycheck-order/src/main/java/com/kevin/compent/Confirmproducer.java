package com.kevin.compent;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-10 21:33
 * @description todo
 **/
public class Confirmproducer implements InitializingBean {
    @Autowired
    RabbitTemplate template;

    @Autowired
    KevinConfirmListener listener;
  public static void main(String[] args) throws IOException, TimeoutException {

      //创建连接工厂
      ConnectionFactory factory=new ConnectionFactory();
      factory.setHost("192.168.248.1");
      factory.setPort(5672);
      factory.setVirtualHost("/");
      factory.setUsername("guest");
      factory.setPassword("guest");
      factory.setConnectionTimeout(100000);

      //创建连接
      Connection connection=factory.newConnection();
      //创建一个channel
      Channel channel=connection.createChannel();

      // 设置消息投递模式（确认模式）
      channel.confirmSelect();

      String exchangeName="kevin.confirm";
      String routingkey="kevin.confirm.key";

      //设置消息属性
      Map<String,Object>info=new HashMap<>();
      info.put("name","kevin");
      info.put("looklike","handsome");

      AMQP.BasicProperties properties=new AMQP.BasicProperties().builder()
              .deliveryMode(2)
              .correlationId(UUID.randomUUID().toString())
              .timestamp(new Date())
              .headers(info)
              .build();


      for (int i=0;i<10;++i){
          String message="new life";
          channel.basicPublish(exchangeName,routingkey,properties,message.getBytes());
      }

      /***
       * 注意：这里不能使用channel.close()，否则消息无法接收确认了
       * ***/
  }

    @Override
    public void afterPropertiesSet() throws Exception {
        template.setConfirmCallback(listener);
    }
}
