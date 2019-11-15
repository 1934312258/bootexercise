package com.kevin.MsgDelegate;

import com.kevin.entity.Order;

import java.io.File;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-11-15 13:39
 * @description todo
 **/
public class MsgDelegate {
    public void handleMessage(String msgBody) {
        System.out.println("MsgDelegate。。。。。。handleMessage"+msgBody);
    }

    public void consumerMsg(String msg){
        System.out.println("MsgDelegate。。。。。。consumerMsg"+msg);
    }

    public void consumerTopicQueue(String msgBody) {
        System.out.println("MsgDelegate。。。。。。consumerTopicQueue"+msgBody);

    }

    public void consumerTopicQueue2(String msgBody) {
        System.out.println("MsgDelegate。。。。。。consumerTopicQueue2"+msgBody);

    }

    /**
     * 处理json
     * @param jsonMap
     */
    public void consumerJsonMessage(Map jsonMap) {
        System.out.println("MsgDelegate ============================处理json"+jsonMap);
    }

    /**
     * 处理order得
     * @param order
     */
    public void consumerJavaObjMessage(Order order) {
        System.out.println("MsgDelegate ============================处理java对象"+order.toString());

    }



    public void consumerFileMessage(File file) {
        System.out.println("MsgDelegate========================处理文件"+file.getName());
    }




}
