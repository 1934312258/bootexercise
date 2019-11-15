package com.kevin.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.bo.MessageBo;
import com.kevin.constants.MqConst;
import com.kevin.entity.MessageContent;
import com.kevin.enumration.MsgstatusEnum;
import com.kevin.mapper.MsgContentMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-12 15:17
 * @description todo
 **/
@Component
@Slf4j
public class MqConsumer {
    /**队列名称*/
    public static final String ORDER_TO_CALLBACK_QUEUE_NAME = "order-to-callback.queue";


    @Autowired
    private MsgContentMapper mapper;


    /**
     * 没有加分布式锁的版本,可能存在重复消费问题
     * @param message
     * @param channel
     * @throws java.io.IOException
     */
    @RabbitListener(queues={ORDER_TO_CALLBACK_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsg(Message message, Channel channel) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        MessageBo messageBo=mapper.readValue(message.getBody(),MessageBo.class);
        log.info("库存服务product====》消费消息：{}",messageBo);
        long deliveryTag=message.getMessageProperties().getDeliveryTag();

        log.info("消费确认消息：{}",messageBo);
        MessageContent content=new MessageContent();
        content.setMsgId(messageBo.getMsgId());
        content.setOrderNo(messageBo.getOrderNo());
        content.setProductNo(messageBo.getProductNo());
        content.setCreateTime(new Date());
        content.setUpdateTime(new Date());
        content.setMaxRetry(MqConst.MAX_RETRY_COUNT);
        content.setExchange(message.getMessageProperties().getReceivedExchange());
        content.setRoutingKey(message.getMessageProperties().getReceivedRoutingKey());
        content.setMsgStatus(MsgstatusEnum.CONSUMER_SUCCESS.getCode());

        //插入消息
        this.mapper.saveMsgContent(content);

        //签收消息
        channel.basicAck(deliveryTag,false);
    }

    @RabbitListener(queues={MqConst.ORDER_TO_PRODUCT_DELAY_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsgWithLock(Message message,Channel channel) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        MessageBo messageBo=mapper.readValue(message.getBody(),MessageBo.class);
        long deliveryTag=message.getMessageProperties().getDeliveryTag();

        //替换延时消息id
        String msgId=messageBo.getMsgId().replace("_delay","");

        MessageContent content=this.mapper.qryMessageContentById(msgId);

        log.info("消费延时消息：{}",messageBo.toString());

        //表示消费者没有发送确认消息，需要回调生产者重新发送消息
        if(content==null){
            //回调订单服务，重新发送消息
            RestTemplate template=new RestTemplate();
            template.postForEntity("http://localhost:8080/retryMsg",messageBo,String.class);
        }

        //签收消息
        channel.basicAck(deliveryTag,false);

    }
}
