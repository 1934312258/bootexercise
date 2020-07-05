package AtomicAndUnsafe.Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    static int[]ints=new int[]{1,2};
    static AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(ints);
    //AtomicIntegerArray使用clone克隆数组副本，不会改变原数组的值
    public static void main(String[] args) {
        atomicIntegerArray.getAndSet(0,3);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(ints[0]);
        if(atomicIntegerArray.get(0)==ints[0]){
            System.out.println("相等");
        }else{
            System.out.println("不相等");
        }
    }
}
