package com.kevin.javaDemo.aspect;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kevin
 * @date 2020-7-7 16:53
 * @description todo
 **/
public class TestMain {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        KevinCalculate calculate = (KevinCalculate) ctx.getBean("calculate");
        int retVal = calculate.add(2,4);
        System.out.println("运算结果是:"+retVal);
        System.out.println(ctx.getBean("kevinLogAspect"));
    }

}
