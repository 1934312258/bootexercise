package com.kevin.javaDemo.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author kevin
 * @date 2020-6-2 16:18
 * @description todo
 **/
public class CyclicBarrierTest implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private int index;

    public CyclicBarrierTest(CyclicBarrier cyclicBarrier,int index){
        this.cyclicBarrier=cyclicBarrier;
        this.index=index;
    }
    @Override
    public void run() {
        System.out.println("index:"+index);
        index--;
        try {
            cyclicBarrier.await();
      System.out.println(index+"开始处理任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
