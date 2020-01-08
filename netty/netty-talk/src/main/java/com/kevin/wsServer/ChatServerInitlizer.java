package com.kevin.wsServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author kevin
 * @date 2019-12-31 16:50
 * @description todo
 **/
public class ChatServerInitlizer extends ChannelInitializer<SocketChannel> {
    private final ChannelGroup group;

    public ChatServerInitlizer(ChannelGroup group){
        this.group=group;
    }
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                //编码http请求
                .addLast(new HttpServerCodec())
//                .addLast(new HttpRequestDecoder())
//                .addLast(new HttpResponseEncoder())
                //聚合解码httpRequest、httpContent/lastHttpContent到fullHttpRequest
                //将多个消息对象聚合为一个fullHttpRequest，相当于粘包，不过需要限制大小，此处为64k
                .addLast(new HttpObjectAggregator(64*1024))
                .addLast(new ChunkedWriteHandler())//方便大文件传输，需要放在文件处理handler的最前面
                .addLast(new HttpRequestHandler("/ws"))
                .addLast(new TextWebSocketFrameHandler(group));
    }
}
