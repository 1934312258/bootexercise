package com.kevin;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;

/**
 * @author kevin
 * @date 2019-10-17 21:18
 * @description todo
 **/
public class ChildrenTest {
    ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("192.168.101.19", 4, event -> {
            System.out.println(event);
        });
    }

    @Test
    public void getChild() throws KeeperException, InterruptedException {
        zooKeeper.getChildren("/kevin", null).stream().forEach(System.out::println);
        Stat stat = new Stat();
        zooKeeper.getChildren("/tuling", null, stat);
        System.out.println(stat);
    }
}
