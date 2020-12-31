package AtomicAndUnsafe.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeInstance {
    public static Unsafe ReflectGetUnsafe() {
        try {
            Field file = Unsafe.class.getDeclaredField("theUnsafe");
            file.setAccessible(true);
            return (Unsafe) file.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
