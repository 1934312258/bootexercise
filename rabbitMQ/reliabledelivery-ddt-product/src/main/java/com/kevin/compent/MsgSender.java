package com.kevin.compent;

import com.kevin.bo.MessageBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2019-11-12 14:51
 * @description todo
 **/
@Component
@Slf4j
public class MsgSender implements InitializingBean {
    @Autowired
    private RabbitTemplate template;

    /**队列名称*/
    public static final String ORDER_TO_PRODUCT_EXCHANGE_NAME = "order-to-product.exchange";

    public static final String PRODUCT_TO_CALLBACK_ROUTING_KEY = "product_to_callback_key";

    public void senderMsg(MessageBo message){
        log.info("发送消息的ID：{}",message.getOrderNo());

        CorrelationData correlationData=new CorrelationData(message.getMsgId()+"_"+message.getOrderNo());

        template.convertAndSend(ORDER_TO_PRODUCT_EXCHANGE_NAME,PRODUCT_TO_CALLBACK_ROUTING_KEY,message,correlationData);

    }



    @Override
    public void afterPropertiesSet() throws Exception {
        //设置消息转换器
        Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
        template.setMessageConverter(converter);
    }
}
