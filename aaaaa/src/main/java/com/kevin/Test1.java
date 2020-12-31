package com.kevin;

import lombok.Data;

/**
 * @author
 * @date 2020-5-27 16:46
 * @description todo
 **/
@Data
public class Test1 implements TestInterface {
    private Integer name;

    @Override
    public void test1() {
        System.out.println("真相是");
    }

    boolean classtrue = true;

    void test2() {
        boolean testtrue = true;
        System.out.println(classtrue + "========" + testtrue);
        testtrue = false;
    }

    public static void main(String[] args) throws InterruptedException {
        Test1 test1 = new Test1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test1.test2();
                test1.classtrue = false;
                test1.test2();
            }
        }).start();

        Thread.sleep(3000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                test1.test2();

            }
        }).start();


    }
}
