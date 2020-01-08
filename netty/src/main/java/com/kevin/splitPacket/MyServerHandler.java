package com.kevin.splitPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author kevin
 * @date 2019-12-23 11:35
 * @description todo
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<MyMessageProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMessageProtocol msg) throws Exception {
       // System.out.println("========服务端接收到的消息如下======");
       // System.out.println("长度="+msg.getLen());
        System.out.println("内容="+new String(msg.getContent(), CharsetUtil.UTF_8));
       // System.out.println("服务端接收到消息包数量"+ (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
