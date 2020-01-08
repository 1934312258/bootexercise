package com.kevin.consumer_limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-11 10:45
 * @description todo
 **/
public class QosRabbitmqConsumer {
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
      String exchangeName="kevin.confirm";
      String exchangeType="direct";
      channel.exchangeDeclare(exchangeName,exchangeType,true,false,null);

      String queueName="kevin.confirm.queue";
      channel.queueDeclare(queueName,true,false,false,null);

      String routingkey="kevin.confirm.key";
      channel.queueBind(queueName,exchangeName,routingkey);

      /**
       * 限流设置：prefetchSize：每条消息大小的设置
       * prefetchCount：标识每次推送多少条消息，一般为一条
       * global;false标识channel级别的 true标识消费级别的,即上面的设置应用于哪个级别
       *以上设置只有在手动ack时才生效，prefetchSize与global rabbitmq暂时没有实现
       * 不论手动签收或者自动签收，或者签不签收，rabbitmq都会把队列中的所有消息一次性全部发送给消费者
       * 当使用Qos后与手动签收后，只有消息被手动签收之后，队列才会将新的消息推送给消费者
       * **/
      channel.basicQos(0,1,false);
  }
}
