package com.kevin.compent;

import com.kevin.bo.MessageBo;
import com.kevin.constants.MqConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2019-11-14 16:15
 * @description todo
 **/
@Component
@Slf4j
public class MsgSender implements InitializingBean {
    @Autowired
    RabbitTemplate template;

    @Autowired
    ConfirmListener listener;

    //订单消息
    public void sendMsg(MessageBo message){
        log.info("发送消息ID：{}",message.getMsgId());

        CorrelationData data=new CorrelationData(message.getMsgId()+"_"+message.getOrderNo());

        template.convertAndSend(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME, MqConst.ORDER_TO_PRODUCT_ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay",MqConst.DELAY_TIME);//设置延迟时间
                return message;
            }
        }, data);
    }

    //延迟消息
    public void sendDelayMsg(MessageBo message){
        log.info("发送消息的ID：{}",message.getMsgId());
        CorrelationData data=new CorrelationData(message.getMsgId()+"_"+message.getOrderNo());

        template.convertAndSend(MqConst.ORDER_TO_PRODUCT_DELAY_EXCHANGE_NAME,MqConst.ORDER_TO_PRODUCT_DELAY_ROUTING_KEY,message,data);
    }




    @Override
    public void afterPropertiesSet() throws Exception {
        template.setConfirmCallback(listener);
        //设置消息转换器
        Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
        template.setMessageConverter(converter);
    }
}
