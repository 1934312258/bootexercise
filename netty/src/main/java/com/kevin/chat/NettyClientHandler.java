package com.kevin.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author kevin
 * @date 2019-12-19 16:40
 * @description todo
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //当通道有读取事件时触发，及服务端发送数据给客户端
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(msg);
    }

}
