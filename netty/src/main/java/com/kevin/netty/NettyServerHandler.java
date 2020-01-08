package com.kevin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @author kevin
 * @date 2019-12-19 15:47
 * @description 自定义handler需要继承netty规定好的某个handlerAdapter
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel， 管道pipeline
     * @param msg 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程"+Thread.currentThread().getName());
        Channel channel=ctx.channel();
        ChannelPipeline pipeline=ctx.pipeline();//本质是一个双向链表，出入站
        //将msg转换成一个byteBuf，类似nio的byteBuffer
        ByteBuf buf= (ByteBuf) msg;
        System.out.println("客户端发送消息是："+buf.toString(CharsetUtil.UTF_8));
       }
    //读取数据完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf= Unpooled.copiedBuffer("HelloClient",CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }
    //处理异常，一般需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }
}
