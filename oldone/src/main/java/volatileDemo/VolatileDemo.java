package volatileDemo;

//结果会有输出，因为有两个线程，当一个线程在读取i的过程中，另一个线程还在给j赋值
//解决方案：1、使用synchronized关键字，2、在一个线程内执行所有操作
public class VolatileDemo {
    static int i = 0, j = 0;

    static synchronized void one() {
        i++;
        j++;
    }

    static synchronized void two() {
        if (i < j) {
            System.out.println("i=" + i + " j=" + j);
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int k = 0; k < 100000; k++) {
                VolatileDemo.one();
            }
        }).start();

        new Thread(() -> {
            for (int k = 0; k < 100000; k++) {
                VolatileDemo.two();
            }
        }).start();
    }
}
