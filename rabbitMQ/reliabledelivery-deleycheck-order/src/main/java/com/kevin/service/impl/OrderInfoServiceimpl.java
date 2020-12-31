package com.kevin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevin.bo.MessageBo;
import com.kevin.compent.MsgSender;
import com.kevin.constants.MqConst;
import com.kevin.entity.MessageContent;
import com.kevin.entity.OrderInfo;
import com.kevin.enumration.MsgstatusEnum;
import com.kevin.mapper.OrderInfoMapper;
import com.kevin.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author kevin
 * @date 2019-11-14 15:23
 * @description todo
 **/
@Slf4j
@Component
public class OrderInfoServiceimpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper mapper;

    @Autowired
    private MsgSender sender;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrderInfo(OrderInfo info) {
        try {
            mapper.saveOrderInfo(info);
        } catch (Exception e) {
            log.error("操作数据库失败：{}", e);
            throw new RuntimeException("操作数据库失败");
        }
    }

    @Override
    public void saveOrderInfoWithMessage(OrderInfo info) throws JsonProcessingException {
        //构建消息对象
        MessageContent content = builderMessageContent(info.getOrderNo(), info.getProductNo());

        //保存数据库
        saveOrderInfo(info);

        //构建消息发送对象
        MessageBo message = new MessageBo();
        message.setMsgId(content.getMsgId());
        message.setOrderNo(content.getOrderNo());
        message.setProductNo(content.getProductNo());

        //发送消息订单消息
        sender.sendMsg(message);

        //发送延迟消息
        sender.sendDelayMsg(message);

    }


    private MessageContent builderMessageContent(long orderNo, Integer productNo) {
        MessageContent content = new MessageContent();
        String msgId = UUID.randomUUID().toString();
        content.setMsgId(msgId);
        content.setCreateTime(new Date());
        content.setUpdateTime(new Date());
        content.setExchange(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME);
        content.setRoutingKey(MqConst.ORDER_TO_PRODUCT_ROUTING_KEY);
        content.setMsgStatus(MsgstatusEnum.SENDING.getCode());
        content.setOrderNo(orderNo);
        content.setProductNo(productNo);
        content.setMaxRetry(MqConst.MSG_RETRY_COUNT);
        return content;
    }
}
