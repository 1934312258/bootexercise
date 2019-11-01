package com.kevin.os;

import org.I0Itec.zkclient.ZkClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author kevin
 * @date 2019-10-17 21:59
 * @description todo
 **/
public class Agent {
    private static Agent  ourInstance=new Agent();
    private String server="192.168.101.19";
    private static final String rootPath="/kevin";
    private static final String servicePath=rootPath+"/service";
    private String nodePath;//当前节点路径kevin/service000000000001
    private Thread stateThread;
    private ZkClient zkClient;
    public static Agent getInstance(){
        return ourInstance;
    }

    //更新服务节点状态
//    public String getOsInfo(){
//
//    }

//    public static String getLocalIp(){
//        InetAddress addr;
//        try {
//            addr=InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return addr.getHostAddress();
//    }

    public void buildRoot(){
        if(!zkClient.exists(rootPath)){
            zkClient.createPersistent(rootPath);
        }
    }
}
