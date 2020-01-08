package com.kevin.service.impl;

import com.kevin.service.ChatService;
import com.kevin.util.CacheLoader;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author kevin
 * @date 2019-12-31 15:50
 * @description todo
 **/
@Service
public class ChatServiceimpl implements ChatService {
    @Override
    public void register(ChannelHandlerContext ctx, Map<String, Object> msg) {
        CacheLoader.channelMap.put(Long.parseLong(msg.get("userId").toString()),ctx.channel());
    }

    @Override
    public void singleSend(ChannelHandlerContext ctx, Map<String, Object> msg) {
        long to=Long.parseLong(msg.get("to").toString());
        msg.remove("to");
        msg.remove("type");
        CacheLoader.channelMap.get(to).writeAndFlush(new TextWebSocketFrame());
    }
}
