package com.kevin.wsServer;

import com.kevin.service.ChatService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.ServerHandshakeStateEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author kevin
 * @date 2019-12-31 15:36
 * @description todo
 **/
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup group;
    @Autowired
    ChatService service;

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //如果ws握手完成
        if (evt == ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            //删除pipeline中处理http请求的handler
            ctx.pipeline().remove(HttpRequestHandler.class);
            group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 将接收的消息通过ChannelGroup转发到所有连接的客户端
        //channelGroup.writeAndFlush(textWebSocketFrame.retain());
        // 前端组装的消息格式是 {"message":{"text":"项目地址","date":"2018-11-28T02:13:52.437Z"},"to":2,"from":1}
        Map<String, Object> msg = null;//将textWebSocketFrame转换为map，使用json转，也可以直接转为json对象;
        String type = (String) msg.get("type");
        switch (type) {
            case "REGISTER":
                service.register(channelHandlerContext, msg);
                break;
            case "SINGLE_SENDING":
                service.singleSend(channelHandlerContext, msg);
                break;
        }
    }
}
