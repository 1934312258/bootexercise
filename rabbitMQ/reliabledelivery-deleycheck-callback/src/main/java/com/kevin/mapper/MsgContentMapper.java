package com.kevin.mapper;

import com.kevin.entity.MessageContent;

public interface MsgContentMapper {
    int saveMsgContent(MessageContent messageContent);

    MessageContent qryMessageContentById(String msgId);

}
