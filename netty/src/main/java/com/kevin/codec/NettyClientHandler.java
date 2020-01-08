package com.kevin.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author kevin
 * @date 2019-12-22 12:41
 * @description todo
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送数据");
        ctx.writeAndFlush("测试string编码");
        //测试发送对象
        ctx.writeAndFlush(new User(1,"kevin"));
        //测试自定义long数据编码解析器
        ctx.writeAndFlush(100L);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
