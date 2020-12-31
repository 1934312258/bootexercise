package com.kevin.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.entity.Order;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author kevin
 * @date 2019-11-15 21:33
 * @description todo
 **/
@Component
@Slf4j
public class MsgReceiver {

    @RabbitListener(queues = {"springbootQueue"})
    public void consumerMsg(Message message, Channel channel) throws IOException {
        System.out.println(Thread.currentThread().getName() + "接收到来自springbootQueue");
        log.info("监听SpringbootQueue消费消息====：{}", new String(message.getBody()));
        //手工签收
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);

    }

    //消费延时消息
    @RabbitListener(queues = "springbootDelayQueue")
    public void consumerDelayMsg(Message message, Channel channel) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.readValue(message.getBody(), Order.class);
        log.info("在{}，签收：{}", LocalDateTime.now().format(formatter), order);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    //不推荐此种用法，开发中需要把队列、交换机、绑定配置到专门的配置类中
    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(
                    name = "springbootQueue2",
                    durable = "true",
                    autoDelete = "false",
                    exclusive = "false"
            ),
            exchange = @Exchange(
                    name = "springboot.direct.exchange",
                    type = "direct",
                    durable = "true",
                    autoDelete = "fasle"),
            key = {"springboot.key2"}
    )
    )
    public void consumerMsg2(Message message, Channel channel) throws IOException {
        System.out.println(Thread.currentThread().getName() + "接收到来自springbootQueue：");
        log.info("监听SpringbootQueue消费消息=====：{}", new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = {"springbootQueue3"})
    public void consumerOrder(Message message, Channel channel) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.readValue(message.getBody(), Order.class);
        log.info("监听springbootQueue3消费消息：{}", order.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = {"springbootclusterQueue3"})
    public void testclusterqueue(Message message, Channel channel) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.readValue(message.getBody(), Order.class);
        log.info("监听springbootQueue3消费消息：{}", order.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
