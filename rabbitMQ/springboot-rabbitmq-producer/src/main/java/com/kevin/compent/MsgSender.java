package com.kevin.compent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.entity.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @author kevin
 * @date 2019-11-18 9:53
 * @description todo
 **/
@Component
public class MsgSender {
    @Autowired
    private RabbitTemplate template;

    public void sendMsg(String msg, Map<String, Object> args) {
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().putAll(args);

        //构建消息对象
        Message message = new Message(msg.getBytes(), properties);

        //构建correlationData用于做可靠性投递，ID：必须为全局唯一的，根据业务规则
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString());

        //开启确认模式
        template.setConfirmCallback(new ConfirmCallBack());

        //开启消息可达监听
        template.setReturnCallback(new ReturnCallBack());

        //错误的交换机
        template.convertAndSend("springboot.direct.exchange", "springboot.key2asdfasdfasfasdfsfasdf", message, data);

        //错误的队列
        template.convertAndSend("springboot.direct.exchange", "springboot.key2asdfasdfasfasdfsfasdf", message, data);
    }

    public void sendOrderMsg(Order order) throws JsonProcessingException {
        // 构建correlationData用于做可靠性投递，ID：必须为全局唯一的，根据业务规则
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString());

        // 开启确认模式
        template.setConfirmCallback(new ConfirmCallBack());

        // 开启消息可达监听
        template.setReturnCallback(new ReturnCallBack());

        //使用org.springframework.amqp.core.Message 包装对象发送
        ObjectMapper mapper = new ObjectMapper();
        String orderJson = mapper.writeValueAsString(order);
        MessageProperties properties = new MessageProperties();

        //用于将json串通过Jackson2JsonMessageConverter转换为map形式被消费端接收即消费端接收到
        //json数据而非byte数组
        properties.setContentType("application/json");
        Message message = new Message(orderJson.getBytes(), properties);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.convertAndSend("springboot.direct.exchange", "springboot.key3", message, data);

        //直接发送对象
        template.convertAndSend("springboot.direct.exchange", "springboot.key2", order, data);

    }


    public void sendDelayMessage(Order order) {
        // 构建correlationData用于做可靠性投递，ID：必须为全局唯一的  根据业务规则
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        template.setConfirmCallback(new ConfirmCallBack());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.convertAndSend("delayExchange", "springboot.delay.key", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 10000);//设置延迟时间
                return message;
            }
        }, data);
    }

    public void sendMsgToCluster(String msg, Map<String, Object> args) {
        MessageProperties properties = new MessageProperties();
        properties.getHeaders().putAll(args);

        Message message = new Message(msg.getBytes(), properties);

        CorrelationData data = new CorrelationData(UUID.randomUUID().toString());

        template.setConfirmCallback(new ConfirmCallBack());

        template.setReturnCallback(new ReturnCallBack());

        template.convertAndSend("springboot.direct.exchange", "rabbit.cluster.key", message, data);
    }
}
