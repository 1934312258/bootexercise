package com.kevin;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

/**
 * @author kevin
 * @date 2019-10-10 20:30
 * @description zookeeper原生客户端的使用
 **/
public class DataTest {
    ZooKeeper zooKeeper;
    @Before
    public void init() throws IOException {
       // String conn="192.168.1.19:2181,192.168.1.19:2182,192.168.1.19:2183";
        String conn="192.168.101.100:2181";
        zooKeeper=new ZooKeeper(conn, 8000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath());
                System.out.println(watchedEvent);
            }
        });
    }
    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[]data=zooKeeper.getData("/zookeeper",true,null);
        System.out.println(new String(data));
    }

}
