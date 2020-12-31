package AtomicAndUnsafe.Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedRerenceTest {
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1, 1);

    //在使用原子类的过程中，版本号值一定要通过volitile获取内存中的最新值否则会使用原来的值导致出错
    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            int stampe = atomicStampedReference.getStamp();
            System.out.println("操作线程" + Thread.currentThread().getName() + "stampe=" + stampe + "初始值a=" + atomicStampedReference.getReference());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Boolean isCasSuccess = atomicStampedReference.compareAndSet(1, 2, stampe, stampe + 1);
            System.out.println("操作线程" + Thread.currentThread().getName() + "stampe=" + atomicStampedReference.getStamp() + "是否操作成功" + isCasSuccess);
        }, "主线程");

        Thread other = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread().getName() + "stamp=" + atomicStampedReference.getStamp() + "操作后的值" + atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread().getName() + "stamp=" + atomicStampedReference.getStamp() + "操作后的值" + atomicStampedReference.getReference());

        }, "干扰线程");

        main.start();
        other.start();
    }
}
