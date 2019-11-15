package com.kevin.compent;

import com.kevin.bo.MessageBo;
import com.kevin.constants.MqConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2019-11-13 21:05
 * @description todo
 **/
@Component
@Slf4j
public class MsgSender implements InitializingBean {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private MsgConfirmListener confirm;

    @Autowired
    private MsgReturnListener returnListener;

    public void sendMsg(MessageBo message){
        log.info("发送的消息Id：{}",message.getMsgId());
        CorrelationData correlation=new CorrelationData(message.getMsgId());
        template.convertAndSend(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME,MqConst.ORDER_TO_PRODUCT_ROUTING_KEY,message,correlation);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        template.setConfirmCallback(confirm);
        template.setReturnCallback(returnListener);
        //设置消息转换器
        Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
        template.setMessageConverter(converter);
    }
}
