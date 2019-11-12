package com.kevin.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-7 13:30
 * @description todo
 **/
public class RabbitmqConsumer {
  public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
    //创建连接工厂
    ConnectionFactory factory=new ConnectionFactory();
    factory.setHost("192.168.159.8");
    factory.setPort(5672);
    factory.setVirtualHost("kevin");
    factory.setUsername("kevin");
    factory.setPassword("kevin");

    //创建连接
    Connection connection=factory.newConnection();
    //创建一个channel
    Channel channel=connection.createChannel();
    //声明队列
    String queueName="kevin-queue-01";
    /*
    * durable：是否持久化，队列的声明默认是存放在内存中的，如果rabbit重启会丢失，如果想要重启之后依然存在就要使用队列持久化
    * 保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
    * exclusive：该队列是否有私有的private，如果不是排外的，可以使用两个消费者都访问同一个队列
    * 如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，如果强制访问回报异常
    * com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=405,
    *  reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)
    *一般等于true的话用于一个队列只能有一个消费者来消费的场景
    * autodelete：是否自动删除，当最后一个消费者断开连接之后队列是否自动删除，可以通过RabbitMQ Management
    * 查看某个队列的消费者数量，当consumers=0时队列就会自动删除
    * */
    channel.queueDeclare(queueName,true,false,true,null);

    //创建消费者
    QueueingConsumer consumer=new QueueingConsumer(channel);
    channel.basicConsume(queueName,true,consumer);
    while (true){
      QueueingConsumer.Delivery delivery=consumer.nextDelivery();
      String message=new String(delivery.getBody());
      System.out.println("消费信息"+message);
    }
  }



}
