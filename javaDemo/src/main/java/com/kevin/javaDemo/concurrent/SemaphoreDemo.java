package com.kevin.javaDemo.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author kevin
 * @date 2020-6-2 14:07
 * @description todo
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; ++i) {
            new Thread(new Task(semaphore, "kevin" + i)).start();
        }
    }

    static class Task extends Thread {
        Semaphore semaphore;

        public Task(Semaphore semaphore, String tname) {
            this.semaphore = semaphore;
            this.setName(tname);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ": aquire() at time:" + System.currentTimeMillis());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + ": aquire() at time: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

