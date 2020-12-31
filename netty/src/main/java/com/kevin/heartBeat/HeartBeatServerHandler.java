package com.kevin.heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;

/**
 * @author kevin
 * @date 2019-12-23 8:46
 * @description todo
 **/
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<String> {
    int readIdleTimes = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("======> [server] message received:" + s);
        if ("Heartbeat packet".equals(s)) {
            ctx.channel().writeAndFlush("ok");
        } else {
            System.out.println("其他信息处理");
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;
        String eventType = null;
        switch (event.state()) {
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes++;
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                //不处理
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                //不处理
                break;
        }
        System.out.println(ctx.channel().remoteAddress() + "超时事件：" + eventType);
        if (readIdleTimes > 3) {
            System.out.println("[server]读空闲超过三次，关闭连接，释放更多资源");
            ctx.channel().writeAndFlush("idle close");
            ctx.channel().close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "is active ===");
    }
}
