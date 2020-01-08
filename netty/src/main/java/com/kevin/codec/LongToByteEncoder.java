package com.kevin.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author kevin
 * @date 2019-12-22 10:49
 * @description 编码器，用于出站操作，将消息编码为字节
 **/
public class LongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long msg, ByteBuf out) throws Exception {
        System.out.println("LongToByteEncoder encoder被调用");
        System.out.println("msg="+msg);
        out.writeLong(msg);
    }
}
