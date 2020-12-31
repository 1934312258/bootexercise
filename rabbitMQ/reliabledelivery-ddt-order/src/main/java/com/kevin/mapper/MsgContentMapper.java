package com.kevin.mapper;

import com.kevin.entity.MessageContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsgContentMapper {
    int saveMsgContent(MessageContent content);

    int updateMsgStatus(MessageContent content);

    List<MessageContent> queryMsgs(@Param("msgStatus") Integer status, @Param("timeDiff") Integer timeDeff);

    void updateMsgRetryCount(String msgId);
}
