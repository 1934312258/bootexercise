package com.kevin.wsServer;

import com.kevin.util.CacheLoader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin
 * @date 2020-1-2 11:39
 * @description todo
 **/
public class WebSocketServer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    ChannelFuture channelFuture;

    public void build() {
        try {
            long begin = System.currentTimeMillis();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)//队列长度，用于存放连接请求
                    .option(ChannelOption.TCP_NODELAY, true)//tcp_nodelay算法，尽可能发送大块数据，减少充斥的小块数据
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//当设置为true的时候，TCP会实现监控连接是否有效，当连接处于空闲状态的时候，超过了2个小时，本地的TCP实现会发送一个数据包给远程的 socket，如果远程没有发回响应，TCP会持续尝试11分钟，知道响应为止，如果在12分钟的时候还没响应，TCP尝试关闭socket连接。
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)//配置池化缓冲池，复用byteBuf
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)//容量动态调整的接收缓冲区分配器，默认配置，可以不用手动配置
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(64 * 1024))//固定容量接收缓冲区分配器，容量不够可以扩容，相对浪费内存
                    .childHandler(new ChatServerInitlizer(CacheLoader.group));
            long end = System.currentTimeMillis();
            logger.info("Netty WebSocket服务器启动完成，耗时" + (end - begin) + "ms,已绑定端口" + "8080,阻塞式等候客户端连接");
            channelFuture = bootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("netty 服务端已关闭");
        }
    }

    @Override
    public void run() {
        build();
    }

    /**
     * 此处不太明白
     * 描述：关闭Netty Websocket服务器，主要是释放连接
     * 连接包括：服务器连接serverChannel，
     * 客户端TCP处理连接bossGroup，
     * 客户端I/O操作连接workerGroup
     * <p>
     * 若只使用
     * bossGroupFuture = bossGroup.shutdownGracefully();
     * workerGroupFuture = workerGroup.shutdownGracefully();
     * 会造成内存泄漏。
     */
    public void close() {
        channelFuture.channel().close();
        Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
        Future<?> workerGroupFuture = workerGroup.shutdownGracefully();
        try {
            bossGroupFuture.await();
            workerGroupFuture.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
