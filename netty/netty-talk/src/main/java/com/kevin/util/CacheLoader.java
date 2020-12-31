package com.kevin.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author kevin
 * @date 2019-12-31 15:17
 * @description todo
 **/
public class CacheLoader {
    //存放所有的注册存活的channel
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //以用户id为key，具体的的channel为value，用于一对一发送消息
    public static Map<Long, Channel> channelMap = new ConcurrentHashMap<>();

}
