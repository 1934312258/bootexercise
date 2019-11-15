package com.kevin.compent;

import com.kevin.enumration.OrderStatusEnum;
import com.kevin.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2019-11-14 15:25
 * @description todo
 **/
@Slf4j
@Component
public class ConfirmListener implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private OrderInfoMapper mapper;
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId=correlationData.getId();
        if(ack){
           log.info("成功消费消息：{}",msgId);
        }else{
            dealMsgNack(msgId);
        }
    }


    private void dealMsgNack(String msgId){
        //表示是业务信息没有发送到broker中，那么我们需要删除订单（真正的场景是更新订单状态为作废状态）
        if(!msgId.contains("delay")){
            log.info("发送消息失败：{}",msgId);
            long orderNo=Long.parseLong(msgId.split("_")[1]);
            //删除订单
            updateOrderStatus(orderNo);
        }else{
            //检查消息发送失败，那么不会做可靠性检查，需要重新发送消息
            log.info("延时消息没有发送成功：{}",msgId);
        }
    }

    private void updateOrderStatus(long orderNo){
        mapper.updateOrderStatusById(orderNo, OrderStatusEnum.ERROR.getCode());
    }
}
