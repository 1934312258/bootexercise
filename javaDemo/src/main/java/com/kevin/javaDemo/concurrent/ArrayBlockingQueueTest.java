package com.kevin.javaDemo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaowenjian
 * @since 2022/2/10 15:07
 */
public class ArrayBlockingQueueTest {

    static int test(int a){
        System.out.println(a);
        return ++a;
    }

    static void test1(int a){
        System.out.println(a);
    }

    public static void main(String[] args) throws InterruptedException {
        test1(test(3));


        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);
        // 添加，add最终调用的也是offer方法
        queue.add(new Object());
        queue.offer(new Object());
        // 当队列满时阻塞生产者固定时间
        queue.offer(new Object(),10, TimeUnit.SECONDS);
        // 当队列满时阻塞生产者
        queue.put(new Object());


        //获取
        //peek获取任务不会触发条件队列
        queue.peek();
        queue.poll();
        // 队列为空会阻塞消费者
        queue.take();
    }


}
