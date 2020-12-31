package com.kevin.service;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

public interface ChatService {
    public void register(ChannelHandlerContext ctx, Map<String, Object> msg);

    public void singleSend(ChannelHandlerContext ctx, Map<String, Object> msg);
}
