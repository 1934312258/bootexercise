package com.kevin.dlx;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-11 13:30
 * @description todo
 **/
public class DlxConsumer extends DefaultConsumer {
    private Channel channel;
    public DlxConsumer(Channel channel) {
        super(channel);
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        //消费端拒绝签收，并且不支持重回队列，那么该条消息就是一条死信消息
        //b:multiple 是否支持批量，b1是否支持重回队列
        channel.basicNack(envelope.getDeliveryTag(),false,false);

        //channel.basicAck(envelope.getDeliveryTag(),false);
    }
}
