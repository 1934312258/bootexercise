package ThreadLocal;

//创建线程的id从10开始
public class ThreadDemo {
    private static final ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                int num = local.get().intValue();
                num += 5;
                System.out.println(Thread.currentThread().getName() + ":" + num);
                System.out.println(Thread.currentThread().getId());//线程id从10开始
            }, "Thread-" + i);
        }
        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }
    }
}
