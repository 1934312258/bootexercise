package com.kevin.javaDemo.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author kevin
 * @date 2020-6-2 16:25
 * @description 栅栏屏障， 让一组线程到达一个屏障（也可以叫同步点） 时被阻塞， 直到最后一个线程 到达屏障时， 屏障才会开门， 所有被屏障拦截的线程才会继续运行。
 *     CyclicBarrier默认的构造方法是CyclicBarrier（int parties） ， 其参数表示屏障拦截的线 程数量，
 *     每个线程调用await方法告CyclicBarrier我已经到达了屏障， 然后当前线程被阻塞。
 *
 *
 *
 *     <p>应用场景 可以用于多线程计算数据， 最后合并计算结果的场景。 例如， 用一个Excel保存了用户 所有银行流水， 每个Sheet保存一个账户近一年的每笔银行流水，
 *     现在需要统计用户的日 均 银行流水， 先用多线程处理每个sheet里的银行流水， 都执行完之后， 得到每个sheet的日 均银行流水， 最后，
 *     再用barrierAction用这些线程的计算结果， 计算出整个Excel的日 均银行流水。
 */
public class CyclicBarrierDemo {
  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
      CyclicBarrier cyclicBarrier=new CyclicBarrier(11, new Runnable() {
          @Override
          public void run() {
              System.out.println("所有特工到达屏障， 准备开始执行秘密任务");
          }
      });
      for (int i = 0; i < 10; i++) {
          new Thread(new CyclicBarrierTest(cyclicBarrier, i) ).start() ;
      }
    new Runnable() {

      @Override
      public void run() {
        System.out.println("ninengzhixingma ");
      }
    }.run();
      cyclicBarrier.await() ;
      System.out.println("全部到达屏障. . . . ") ;
  }
}
