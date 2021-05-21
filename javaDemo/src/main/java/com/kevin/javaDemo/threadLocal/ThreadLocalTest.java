package com.kevin.javaDemo.threadLocal;

/**
 * <p>
 *
 * </p>
 *
 * @author kevin
 * @since 2021/5/6 14:54
 */
public class ThreadLocalTest {

    /**
     * 在实体内定义threadLocal变量，用于线程安全，并且保证任何位置均可获取到该对象
     *
     * **/
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            People people = People.getPeople();
            people.setAge(18);
            people.setName("kevin");

            System.out.println(people.getAge());
        });

        Thread t2 = new Thread(()->{
            People people = People.getPeople();
            System.out.println(people.getAge());
            people.setAge(19);
            people.setName("zwj");

            System.out.println(people.getAge());
        });

        t1.start();
        t2.start();

    }
}
