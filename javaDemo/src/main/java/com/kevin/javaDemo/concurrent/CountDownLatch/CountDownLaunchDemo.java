package com.kevin.javaDemo.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author kevin
 * @date 2020-6-2 15:15
 * @description 比如陪媳妇去看病。 医院里边排队的人很多， 如果一个人的话， 要先看大夫， 看完大夫再去排队交钱取药。 现在我们是双核， 可以同时做这两个事（多线程） 。
 * 假设看大夫花3秒钟， 排队交费取药花5秒钟。 我们同时搞的话， 5秒钟我们就能完成， 然后 一起回家（回到主线程） 。
 */
public class CountDownLaunchDemo {
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new SeeDockerTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();
        //主线程等待两个任务完成之后在继续执行
        countDownLatch.await();
        System.out.println("任务执行完成" + (System.currentTimeMillis() - now));
    }
}
