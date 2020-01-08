package com.kevin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author kevin
 * @date 2019-12-20 15:17
 * @description todo
 **/
public class NettyByteBuf {
  public static void main(String[] args) {
      //创建bytebuf对象，该对象包含一个字节数组
      //通过readindex和writeindex和capacity，将buffer分成三个区域
      //已经读取的区域：[0，readindex]
      //可读区域：[readindex,writeindex]
      //可写的区域：[writeindex,capacity]
      ByteBuf byteBuf= Unpooled.buffer(10);
      System.out.println("byteBuf="+byteBuf);
      for(int i=0;i<8;i++){
          byteBuf.writeByte(i);
      }
      System.out.println("byteBuf="+byteBuf);
      for(int i=0;i<5;i++){
        System.out.println(byteBuf.getByte(i));
      }
      System.out.println("byteBuf="+byteBuf);
      //使用readByte时可读索引下标才会改变
      for(int i=0;i<5;i++){
          System.out.println(byteBuf.readByte());
      }
      System.out.println("byteBuf="+byteBuf);
      //使用unpooled工具类创建byteBuf
      ByteBuf byteBuf1=Unpooled.copiedBuffer("Hello,zhuge!", CharsetUtil.UTF_8);
      //适用相关的方法
      if(byteBuf1.hasArray()){
          byte[]content=byteBuf1.array();
         // 将content转成字符串
         System.out.println(new String(content,CharsetUtil.UTF_8));
          System.out.println("byteBuf1="+byteBuf1);
          System.out.println(byteBuf1.readerIndex());
          System.out.println(byteBuf1.writerIndex());
          System.out.println(byteBuf1.capacity());
          System. out. println(byteBuf1. getByte(0) ) ; // 获取数组0这个位置的字符h的ascii码， h=104
          int len = byteBuf1. readableBytes() ; //可读的字节数 12
          System. out. println("len=" + len) ;
          //使用for取出各个字节
          for(int i=0;i<len;i++){
            System.out.println(byteBuf1.getByte(i));
          }
      // 范围读取
      System.out.println(byteBuf1. getCharSequence(0, 6, CharsetUtil. UTF_8) );
      System. out. println(byteBuf1. getCharSequence(6, 6, CharsetUtil. UTF_8) ) ;
      }
  }
}
