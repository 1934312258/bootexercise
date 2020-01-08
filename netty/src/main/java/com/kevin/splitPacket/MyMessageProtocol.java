package com.kevin.splitPacket;

/**
 * @author kevin
 * @date 2019-12-23 10:47
 * @description todo
 **/
public class MyMessageProtocol {
    //定义一次发送包体的长度
    private int len;
    //一次发送包体的内容
    private byte[]content;
    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }



}
