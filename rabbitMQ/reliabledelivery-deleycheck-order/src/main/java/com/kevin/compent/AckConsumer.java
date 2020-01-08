package com.kevin.compent;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-10 14:02
 * @description todo
 **/
public class AckConsumer extends DefaultConsumer {
    private Channel channel;

    public AckConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
       try{
        //模拟业务
        Integer mark= (Integer) properties.getHeaders().get("mark");
        if(mark!=0){
            System.out.println("消费消息："+new String(body));
            //手动签收，服务端会将该条消息删除，false为multiple，删除一条，true为删除多条
            channel.basicAck(envelope.getDeliveryTag(),false);
        }else{
            throw new RuntimeException("模拟业务异常");
        }
        }catch (Exception e){
            System.out.println("消费消息异常："+new String(body));
            //重回队列,在手动签收模式下重回队列会死循环，卡在这条消息上
            channel.basicNack(envelope.getDeliveryTag(),false,true);
            //channel.basicNack(envelope.getDeliveryTag(),false,false);
         }
    }
}
