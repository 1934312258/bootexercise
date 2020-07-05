package AtomicAndUnsafe.Atomic;

import AtomicAndUnsafe.utils.UnsafeInstance;
import sun.misc.Unsafe;

public class AtomicStudentAgeUpdater {
    private String name;
    private volatile int age;

    private static final Unsafe unsafe= UnsafeInstance.ReflectGetUnsafe();
    private static final long valueOffset ;

    static{
        try {
            //valueOffset=unsafe.objectFieldOffset(AtomicStudentAgeUpdater.class.getDeclaredField("age"));
            valueOffset = unsafe.objectFieldOffset(AtomicStudentAgeUpdater.class.getDeclaredField("age"));
            System.out.println(valueOffset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);//运行时抛出异常，故final变量可以不初始化
        }
    }
    public AtomicStudentAgeUpdater(String name,int age){
        this.name=name;
        this.age=age;
    }

    public void compareAndSwapAge(int old,int target){
        unsafe.compareAndSwapInt(this,valueOffset,old,target);
    }

    private int getAge(){
        return this.age;
    }

    public static void main(String[] args) {
        AtomicStudentAgeUpdater updater=new AtomicStudentAgeUpdater("Kevin",29);
        updater.compareAndSwapAge(29,18);
        System.out.println("kevin的真实年龄为"+updater.getAge());
    }
}
