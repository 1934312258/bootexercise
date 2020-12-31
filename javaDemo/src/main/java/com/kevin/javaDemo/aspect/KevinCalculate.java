package com.kevin.javaDemo.aspect;

/**
 * @author kevin
 * @date 2020-7-7 9:36
 * @description todo
 **/
public class KevinCalculate implements Calculate {
    @Override
    public int add(int a, int b) {
        System.out.println("执行目标方法：add");
        return a + b;
    }

    @Override
    public int reduce(int a, int b) {
        System.out.println("执行目标方法：reduce");
        return a - b;
    }

}
