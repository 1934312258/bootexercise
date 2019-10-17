package com.kevin;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void getData2() throws KeeperException, InterruptedException {
        byte[]data=zooKeeper.getData("/zookeeper",true,null);
        System.out.println(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    public void getData3() throws KeeperException, InterruptedException {
        Stat stat=new Stat();
           zooKeeper.getData("/zookeeper", new Watcher() {
                       @Override
                       public void process(WatchedEvent event) {
                           try {
                               zooKeeper.getData(event.getPath(), this, null);
                           } catch (KeeperException e) {
                               e.printStackTrace();
                           } catch (InterruptedException e) {

                               e.printStackTrace();
                           }
                           System.out.println(event.getPath());
                       }
                   }, stat);
        System.out.println(stat);
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void getData4() throws InterruptedException {
        zooKeeper.getData("/kevin", false, new AsyncCallback.DataCallback(){
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println(stat);
            }
        },"");
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void getChild() throws KeeperException, InterruptedException {
        List<String> child=zooKeeper.getChildren("/kevin",false);
        child.stream().forEach(System.out::println);
    }

    @Test
    public void createData() throws KeeperException, InterruptedException {
        List<ACL>list=new ArrayList<>();
        int perm= Perms.ADMIN| Perms.READ;
        ACL acl=new ACL(perm,new Id("world","anyone"));
        ACL acl2=new ACL(perm,new Id("ip","192.168.101.19"));
        ACL acl3=new ACL(perm,new Id("ip","192.168.101.100"));
        list.add(acl);
        list.add(acl2);
        list.add(acl3);
        zooKeeper.create("/kevin/handsome","hello".getBytes(),list, CreateMode.PERSISTENT);
    }

    @Test
    public void getChild2() throws InterruptedException, KeeperException {
        List<String>list=zooKeeper.getChildren("/tuling", event-> {
                try {
                    zooKeeper.getChildren(event.getPath(),false);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        list.stream().forEach(System.out::println);
        Thread.sleep(Long.MAX_VALUE);
    }
}
