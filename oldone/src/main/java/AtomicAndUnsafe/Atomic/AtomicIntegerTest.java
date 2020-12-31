package AtomicAndUnsafe.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    atomicInteger.incrementAndGet();
                    System.out.println(Thread.currentThread());
                    System.out.println(atomicInteger.get());
                }
            }).start();
        }
        try {
            Thread.sleep(1000);//不休眠打印出的数据是线程未执行完毕的数据，故不为10
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());
    }
}
