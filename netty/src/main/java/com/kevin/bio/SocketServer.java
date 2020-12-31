package com.kevin.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author kevin
 * @date 2019-12-17 11:23
 * @description todo
 **/
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接。。");
            System.out.println(Thread.currentThread().getName() + Thread.currentThread().isAlive());
            Socket socket = serverSocket.accept();//阻塞方法，没有客户端连接时就阻塞
            System.out.println("有客户端连接了");
            handler(socket);
            //******此处可以使用线程池处理具体业务，然后主线程返回继续处理连接请求，不会造成过多线程的创建于堆积
//      new Thread(
//              new Runnable() {
//                @Override
//                public void run() {
//                  try {
//                      System.out.println("哪个先执行");
//                    handler(socket);
//                  } catch (IOException e) {
//                    e.printStackTrace();
//                  }
//                }
//              })
//          .start();
        }
    }

    private static void handler(Socket socket) throws IOException {
        System.out.println("thread id =" + Thread.currentThread().getId());
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = socket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
            System.out.println("thread id =" + Thread.currentThread().getId());
        }
        socket.getOutputStream().write("helloClient".getBytes());
        socket.getOutputStream().flush();
    }
}
