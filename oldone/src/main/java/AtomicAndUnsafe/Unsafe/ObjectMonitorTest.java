package AtomicAndUnsafe.Unsafe;

import AtomicAndUnsafe.utils.UnsafeInstance;
import sun.misc.Unsafe;

public class ObjectMonitorTest {
    static Object object = new Object();

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeInstance.ReflectGetUnsafe();
        unsafe.monitorEnter(object);
        //中间写业务逻辑==synchronized
        unsafe.monitorExit(object);
    }
}
