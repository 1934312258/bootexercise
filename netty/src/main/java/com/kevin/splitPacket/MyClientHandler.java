package com.kevin.splitPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author kevin
 * @date 2019-12-23 11:19
 * @description todo
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<MyMessageProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageProtocol msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            String msg = "my name is Kevin";
            MyMessageProtocol protocol = new MyMessageProtocol();
            protocol.setLen(msg.getBytes(CharsetUtil.UTF_8).length);
            protocol.setContent(msg.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(protocol);
        }
    }
}
