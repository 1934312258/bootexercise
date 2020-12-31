package com.kevin;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;

/**
 * @author kevin
 * @date 2019-10-17 21:43
 * @description todo
 **/
public class zkClientTest {
    ZkClient zkClient;

    @Before
    public void init() {
        zkClient = new ZkClient("192.168.101.19", 5, 5);
    }
}
