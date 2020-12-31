package volatileDemo;

import java.util.concurrent.TimeUnit;

//线程不被执行，给flag加上volatile关键字即可（此处禁止指令重排序）
//jvm将class转换为操作系统能够执行的指令（JIT编译器）
//在运行时CPU进行指令重排序，先读后写，存在依赖关系则不然，线程需要flag的值，所以先执行两个写操作
public class VolatileDemo1 {
    private volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo1 demo = new VolatileDemo1();
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (demo.flag) {
                    i++;
                }
                System.out.println(i);
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        demo.flag = false;
        System.out.println("被置为false了");
    }
}
