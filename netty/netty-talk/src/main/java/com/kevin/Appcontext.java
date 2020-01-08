package com.kevin;

import com.kevin.wsServer.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author kevin
 * @date 2020-1-2 11:37
 * @description todo
 **/
@Component
@Scope("singleton")
public class Appcontext {
    Logger logger= LoggerFactory.getLogger(Appcontext.class);
    private Thread nettyThread;
    WebSocketServer socketServer=new WebSocketServer();
    /**
     * 描述：Tomcat加载完ApplicationContext-main和netty文件后：
     *      1. 启动Netty WebSocket服务器；
     *      2. 加载用户数据；
     *      3. 加载用户交流群数据。
     */
    @PostConstruct
    public void init(){
        nettyThread=new Thread(socketServer);
        logger.info("开启独立线程，启动netty WebSocket服务器。。。。");
        nettyThread.start();
    }

    /**
     * 描述：Tomcat服务器关闭前需要手动关闭Netty Websocket相关资源，否则会造成内存泄漏。
     *      1. 释放Netty Websocket相关连接；
     *      2. 关闭Netty Websocket服务器线程。（强行关闭，是否有必要？）
     */
    @PreDestroy
    public void close(){
        logger.info("正在释放netty websocket相关链接。。。");
        socketServer.close();
        logger.info("正在关闭netty webSocket服务器线程。。。。。");
        nettyThread.stop();
        logger.info("系统成功关闭！");
    }
}
