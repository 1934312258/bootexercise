package com.kevin.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kevin
 * @date 2019-12-19 15:47
 * @description 自定义handler需要继承netty规定好的某个handlerAdapter
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //GlobalEventExecutor.INSTANCE是全全局的事件执行器
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        //将客户端加入聊天的信息推送给其他在线的客户端
        //该方法将channelGroup中所有的channel遍历，并发送消息
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"上线了"+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+"\n");
        //将当前的channel加入到channelGroup
        channelGroup.add(channel);
        System.out.println(ctx.channel().remoteAddress()+"上线了"+"\n");
    }
    //表示channel处于不活动状态，提示离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //客户端下线后会自动的将channelGroup中的channel剔除
        Channel channel=ctx.channel();
        //将客户端离开信息推送给当前在线的客户
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"下线了"+"\n");
        System.out.println("客户端"+channel.remoteAddress()+"下线了"+"\n");
        System.out.println("channelGroup size"+channelGroup.size());
    }

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel， 管道pipeline
     * @param msg 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取到当前的channel
        Channel channel=ctx.channel();
        //这时我们遍历channelGroup，根据不同的情况，回送不同的消息
        channelGroup.forEach(ch -> {
            if(channel!=ch){
                ch.writeAndFlush("[客户端]"+channel.remoteAddress()+"发送了消息："+msg+"\n");
            }else{//回显自己发送的消息给自己
                ch.writeAndFlush("[自己]发送了消息："+msg+"\n");
            }
                });
       }
    //处理异常，一般需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }
}
