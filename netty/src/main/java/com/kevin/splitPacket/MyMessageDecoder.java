package com.kevin.splitPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author kevin
 * @date 2019-12-23 10:44
 * @description 入站  解码器
 **/
public class MyMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int length = 0;
        System.out.println("myMessageDecoder decode 被调用");
        //需要将得到的二进制字节码转换为myMessageProtocol数据包对象
        if (in.readableBytes() > 4) {
            //*****此处readInt方法会获取前四位字节的int值，然后将byteBuf的读索引后移4位
            if (in.readableBytes() >= 4) {
                if (length == 0) {
                    length = in.readInt();
                }
                if (in.readableBytes() < length) {
                    System.out.println("当前可读数据不够，继续等待");
                    return;
                }
            } else {
                return;
            }
            length = in.readInt();
            byte[] content = new byte[length];
            in.readBytes(content);

            //封装成myMessageProtocol对象，传递到下一个handler业务处理
            MyMessageProtocol protocol = new MyMessageProtocol();
            protocol.setLen(length);
            protocol.setContent(content);
            out.add(protocol);
            length = 0;
        }

    }
}
