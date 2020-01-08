package com.kevin.confirm;

import com.rabbitmq.client.ConfirmListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

import java.io.IOException;

/**
 * @author kevin
 * @date 2019-11-10 21:25
 * @description todo
 **/
public class KevinConfirmListener implements ConfirmListener{
    /**
     *
     * @param deliveryTag 唯一消息Id
     * @param multiple:是否批量
     * @throws IOException
     */
    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前时间："+System.currentTimeMillis()+"KevinConfirmListener handleAck:"+deliveryTag);
    }

    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("KevinConfirmListener handleAck:"+deliveryTag);
    }
}
