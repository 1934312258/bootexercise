package com.kevin.return_listener;

import com.kevin.confirm.KevinConfirmListener;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author
 * @date 2019-11-11 14:36
 * @description todo
 **/
public class ReturnListenerProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.159.8");
        factory.setPort(5672);
        factory.setVirtualHost("kevin");
        factory.setUsername("kevin");
        factory.setPassword("kevin");
        factory.setConnectionTimeout(100000);

        //创建连接
        Connection connection = factory.newConnection();
        //创建一个channel
        Channel channel = connection.createChannel();

        String exchangeName = "kevin.return.direct";
        String okroutingkey = "kevin.return.key.ok";
        String errorroutingkey = "kevin.return.key.error";

        /**
         * 设置监听不可达信息
         * **/

        channel.addReturnListener(new ReturnListener());

        channel.addConfirmListener(new KevinConfirmListener());

        //设置消息属性
        Map<String, Object> info = new HashMap<>();
        info.put("company", "rain");
        info.put("city", "tianjin");

        AMQP.BasicProperties properties = new BasicProperties().builder()
                .deliveryMode(2)
                .correlationId(UUID.randomUUID().toString())
                .timestamp(new Date())
                .headers(info)
                .build();

        String message = "hello kevin.." + System.currentTimeMillis();

        /***
         * 发送消息
         * mandatory;该属性设置为false，那么不可达消息就会被mq broker给删除
         * true：那么mq会调用returnListener来告诉我们业务系统该消息不能成功发送
         * */
        //channel.basicPublish(exchangeName+"gdfgaadf",okroutingkey,true,properties,message.getBytes());
        //错误发送   mandotory为false
        //channel.basicPublish(exchangeName+"--adfasdfasfasf----",okRoutingKey,true,basicProperties,errorMsg1.getBytes());

        String errorMsg2 = "你好 kevin mandotory为true...." + System.currentTimeMillis();

        //错误发送 mandotory 为true
        channel.basicPublish(exchangeName, errorroutingkey, true, properties, errorMsg2.getBytes());
    }
}
