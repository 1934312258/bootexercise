package com.kevin.javaDemo.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author kevin
 * @date 2020-6-2 15:16
 * @description todo
 **/
public class SeeDockerTask implements Runnable {
    private CountDownLatch countDownLatch = new CountDownLatch(2);

    public SeeDockerTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始看医生");
            Thread.sleep(3000);
            System.out.println("看医生结束， 准备离开病房");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }

    }
}
