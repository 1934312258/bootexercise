package AtomicAndUnsafe.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAbaProblemTest {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = atomicInteger.get();
                System.out.println("操作线程" + Thread.currentThread().getName() + "--修改前操作数值:" + a);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Boolean isCasSuccess = atomicInteger.compareAndSet(a, 2);
                if (isCasSuccess) {
                    System.out.println("操作线程" + Thread.currentThread().getName() + "--Cas修改后操作数值:" + atomicInteger.get());
                } else {
                    System.out.println("CAS修改失败");
                }
            }
        }, "主线程");
        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.incrementAndGet();
                System.out.println("操作线程" + Thread.currentThread().getName() + "increment后的值" + atomicInteger.get());
                atomicInteger.decrementAndGet();
                System.out.println("操作线程" + Thread.currentThread().getName() + "decrement" + atomicInteger.get());
            }
        }, "干扰线程");
        main.start();
        other.start();
    }
}
