package com.kevin;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-10-15 22:31
 * @description todo
 **/
public class AclTest {
    ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("192.168.101.19", 2181, event -> {
            System.out.println(event);
        });
    }

    @Test
    public void getAclTest1() throws KeeperException, InterruptedException {
        List<ACL> acl = zooKeeper.getACL("/kevin", null);
        System.out.println(acl);
    }

    @Test
    public void setAclTest() throws KeeperException, InterruptedException {
        List<ACL> list = new ArrayList<>();
        int perm = Perms.ADMIN | Perms.READ | Perms.CREATE;
        System.out.println("===============================" + perm);
        list.add(new ACL(perm, new Id("world", "192.168.101.100")));
        list.add(new ACL(Perms.ADMIN, new Id("world", "anyone")));
        zooKeeper.setACL("/kevin", list, 5);
        System.out.println("===============================" + perm);
    }
}
