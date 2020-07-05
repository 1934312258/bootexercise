package AtomicAndUnsafe.Unsafe;

import AtomicAndUnsafe.utils.UnsafeInstance;
import sun.misc.Unsafe;

public class FenceTest {
   static Unsafe unsafe= UnsafeInstance.ReflectGetUnsafe();

    public static void main(String[] args) {
        unsafe.loadFence();//读屏障
        unsafe.storeFence();//写屏障
        unsafe.fullFence();//读写屏障
    }
}
