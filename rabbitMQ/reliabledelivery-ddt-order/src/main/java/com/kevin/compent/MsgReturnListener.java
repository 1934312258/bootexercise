package com.kevin.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.bo.MessageBo;
import com.kevin.entity.MessageContent;
import com.kevin.enumration.MsgstatusEnum;
import com.kevin.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kevin
 * @date 2019-11-13 14:41
 * @description todo
 */
@Component
@Slf4j
public class MsgReturnListener implements RabbitTemplate.ReturnCallback {
  @Autowired private MsgContentMapper mapper;

  @Override
  public void returnedMessage(
      Message message, int replyCode, String replyText, String exchange, String routingkey) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      MessageBo messageBo = mapper.readValue(message.getBody(), MessageBo.class);
      log.info("无法路由消息内容：{}，cause：{}", messageBo, replyText);

      // 构建消息对象
      MessageContent content = new MessageContent();
      content.setErrorCause(replyText);
      content.setUpdateTime(new Date());
      content.setMsgStatus(MsgstatusEnum.SENDING_FALL.getCode());
      content.setMsgId(messageBo.getMsgId());

      // 更新消息表
      this.mapper.updateMsgStatus(content);
    } catch (Exception e) {
        log.error("更新消息表异常：{}",e);
    }
  }
}