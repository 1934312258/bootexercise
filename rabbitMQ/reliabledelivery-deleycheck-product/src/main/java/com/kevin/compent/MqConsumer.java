package com.kevin.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.bo.MessageBo;
import com.kevin.exception.DefineException;
import com.kevin.service.ProductService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-12 15:17
 * @description todo
 **/
@Component
@Slf4j
public class MqConsumer {
    /**
     * 队列名称
     */
    public static final String ORDER_TO_PRODUCT_QUEUE_NAME = "order-to-product.queue";

    public static final String LOCK_KEY = "lock.rabbit";
    @Autowired
    private ProductService service;

    @Autowired
    private RedisTemplate template;

    @Autowired
    private MsgSender sender;

    /**
     * 没有加分布式锁的版本,可能存在重复消费问题
     *
     * @param message
     * @param channel
     * @throws java.io.IOException
     */
    @RabbitListener(queues = {ORDER_TO_PRODUCT_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsg(Message message, Channel channel) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MessageBo messageBo = mapper.readValue(message.getBody(), MessageBo.class);
        log.info("库存服务product====》消费消息：{}", messageBo);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //更新消息表与业务表
            service.updateProductStore(messageBo);

            //消息签收
            channel.basicAck(deliveryTag, false);

            //发送一条确认消息到callback服务上
            sender.senderMsg(messageBo);
        } catch (Exception e) {
            if (e instanceof DefineException) {
                channel.basicReject(deliveryTag, false);
            }
            log.error("异常：{}", e.getMessage());
        }
    }

    @RabbitListener(queues = {ORDER_TO_PRODUCT_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsgWithLock(Message message, Channel channel) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MessageBo messageBo = mapper.readValue(message.getBody(), MessageBo.class);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        if (template.opsForValue().setIfAbsent(LOCK_KEY + messageBo.getMsgId(), messageBo.getMsgId())) {
            log.info("消费消息：{}", messageBo);
            try {
                //更新消息表与业务表
                service.updateProductStore(messageBo);
                //发送一条确认消费消息到callback服务上
                sender.senderMsg(messageBo);

                //消息签收
                channel.basicAck(deliveryTag, false);
            } catch (Exception e) {
                if (e instanceof DefineException) {
                    DefineException exception = (DefineException) e;
                    log.info("数据业务异常：{}，即将删除分布式锁", exception.getErrMsg());
                    //删除分布式锁
                    template.delete(LOCK_KEY + messageBo.getMsgId());
                }
            }
        } else {
            log.warn("请不要重复消费消息{}", messageBo);
            channel.basicReject(deliveryTag, false);
        }
    }
}
