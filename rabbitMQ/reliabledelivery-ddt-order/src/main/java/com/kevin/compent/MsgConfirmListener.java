package com.kevin.compent;

import com.kevin.entity.MessageContent;
import com.kevin.enumration.MsgstatusEnum;
import com.kevin.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-13 15:01
 * @description todo
 **/
@Component
@Slf4j
public class MsgConfirmListener implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private MsgContentMapper mapper;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = correlationData.getId(); //即correlationId
        if (ack) {
            log.info("消息Id：{}对应的消息被broker签收成功", msgId);
            updateMsgStatusWithAck(msgId);
        } else {
            log.warn("消息Id：{}对应的消息被broker签收失败：{}", msgId, cause);
            updateMsgStatusWithNack(msgId, cause);
        }

    }

    private void updateMsgStatusWithAck(String msgId) {
        MessageContent content = builderUpdateContent(msgId);
        content.setMsgStatus(MsgstatusEnum.SENDING_SUCCESS.getCode());
        mapper.updateMsgStatus(content);
    }

    private void updateMsgStatusWithNack(String msgId, String cause) {
        MessageContent content = builderUpdateContent(msgId);
        content.setMsgStatus(MsgstatusEnum.SENDING_FALL.getCode());
        content.setErrorCause(cause);
        mapper.updateMsgStatus(content);
    }

    private MessageContent builderUpdateContent(String msgId) {
        MessageContent content = new MessageContent();
        content.setMsgId(msgId);
        content.setUpdateTime(new Date());
        return content;
    }
}
