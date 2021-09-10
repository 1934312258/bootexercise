package com.kevin;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    static void test2(Long a, Long b, Long c) {
        a = a + b + c;
        boolean testtrue = true;
        System.out.println(  "========" + testtrue);
        testtrue = false;
    }

    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
        list.add(null);
        if(ObjectUtil.isNotEmpty(list) && ObjectUtil.isNotEmpty(list.get(0))){
            System.out.println();
        }
        if(ObjectUtil.isNotNull(list)){
            System.out.println();
        }
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int re = iterator.next();
            if(re == 2){
                iterator.remove();
            }
            System.out.println(re);
        }
        System.out.println();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                test1.test2();
////                test1.classtrue = false;
////                test1.test2();
//            }
//        }).start();
//
//        Thread.sleep(3000);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                test1.test2();
//
//            }
//        }).start();


    }
}
