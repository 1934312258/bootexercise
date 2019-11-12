package com.kevin.direct_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kevin
 * @date 2019-11-7 16:36
 * @description todo
 **/
public class DirectConsumer2 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
    // 创建连接工厂
    ConnectionFactory factory=new ConnectionFactory();
    factory.setHost("192.168.159.8");
    factory.setPort(5672);
    factory.setUsername("kevin");
    factory.setPassword("kevin");

    //创建连接
    Connection connection=factory.newConnection();
    //创建一个channel
    Channel channel=connection.createChannel();

    String exchangeName="kevin.directChange";
    String exchangrType="kevin.directQueue";
    String queueName="kevin.directQueue";
    String routingKey="kevin.directChange.key";
/**
 * 声明一个交换机
 * type：交换机的类型，常见的有direct，fanout扇形交换机，topic
 * durable：设置是否持久化
 *autodelete：设置是否自动删除，为true时表示自动删除。自动删除的前提是至少有一个队列或者交换器与此交换机绑定
 *之后所有与此交换机绑定的交换器或者队列都与其解绑，不能错误地理解为当与此交换机连接的客户端都断开连接时，rabbitMQ会自动删除本交换器
 * arguments：其他一些结构化的参数，比如：alternate-exchange
 *
 * */
        channel.exchangeDeclare(exchangeName,exchangrType,true,false,null);

        /**
         * 声明一个队列
         * durable：表示rabbitmq关闭则删除队列
         * autodelete：表示表示没有程序和队列建立连接，那么就会自动删除队列
         *
         * */
        channel.queueDeclare(queueName,true,false,false,null);

        //队列与交换机绑定
        channel.queueBind(queueName,exchangeName,routingKey);

        //创建一个消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);

        channel.basicConsume(queueName,true,consumer);

        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("消费信息--"+message);
        }

    }
}
