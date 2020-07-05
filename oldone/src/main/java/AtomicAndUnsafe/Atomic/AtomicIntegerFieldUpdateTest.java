package AtomicAndUnsafe.Atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdateTest {
    //fieldName 为对象中需要原子性改变的实例变量
    static AtomicIntegerFieldUpdater updater=AtomicIntegerFieldUpdater.newUpdater(Student.class,"age");

    public static void main(String[] args) {
        Student student=new Student("kevin",18);
        System.out.println(updater.getAndIncrement(student));
        System.out.println(updater.get(student)+student.getName());
    }

static class Student{
    private String name;
    public volatile int age;

    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }
   private String getName(){
        return this.name;
   }
   private int getAge(){
        return this.age;
   }
}
}
