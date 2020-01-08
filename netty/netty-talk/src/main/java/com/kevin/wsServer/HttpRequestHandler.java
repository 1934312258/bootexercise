package com.kevin.wsServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author kevin
 * @date 2019-12-31 15:23
 * @description todo
 **/
public class HttpRequestHandler  extends SimpleChannelInboundHandler<FullHttpRequest> {
    //webSocket标识
    private final String wsUri;

    public HttpRequestHandler(String wsUri){
        this.wsUri=wsUri;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        //如果是WebSocket请求，请求地址uri等于wsUri,uri()返回端口号之后的路径
        if(wsUri.equalsIgnoreCase(fullHttpRequest.uri())){
            //将消息发送到下一个handler,fullHttpRequest.retain()方法返回的是当前的byteBuf
            ctx.fireChannelRead(fullHttpRequest.retain());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
