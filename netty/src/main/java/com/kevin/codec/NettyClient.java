package com.kevin.codec;

import com.kevin.netty.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author kevin
 * @date 2019-12-19 16:33
 * @description todo
 **/
public class NettyClient {
  public static void main(String[] args) {
    //客户端需要一个事件循环组
      EventLoopGroup group=new NioEventLoopGroup();
      //创建客户端启动对象
      //注意客户端使用的不是ServerBootstrap而是bootstrap
      Bootstrap bootstrap=new Bootstrap();
      try {
      //设置相关参数
      bootstrap.group(group)
              .channel(NioSocketChannel.class)//使用NioServerSocketChannel作为客户端的通道实现
              .handler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  protected void initChannel(SocketChannel channel) throws Exception {
                      ChannelPipeline pipeline=channel.pipeline();

                      //加入处理器
                      pipeline.addLast(new ByteToLongDecoder());//解码器入站
                      pipeline.addLast(new LongToByteEncoder());//编码器出站
                      pipeline.addLast(new ObjectEncoder());//对象类型编码器
                     pipeline.addLast(new NettyClientHandler());
                  }
              });
      System.out.println("netty client start");
      //启动客户端去连接服务端
      ChannelFuture channelFuture=bootstrap.connect("127.0.0.1",9000).sync();
      //对关闭通道进行监听

          channelFuture.channel().closeFuture().sync();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }finally{
          group.shutdownGracefully();
      }
  }
}
