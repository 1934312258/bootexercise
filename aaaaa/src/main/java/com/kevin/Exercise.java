package com.kevin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kevin
 * @date 2019-11-6 13:36
 * @description todo
 **/
public class Exercise {

    public static void main(String[] args) throws Exception {
        Long dis = 1896L;
        BigDecimal bigDecimal = new BigDecimal(dis);
        bigDecimal = bigDecimal.divide(new BigDecimal(1000),1,BigDecimal.ROUND_HALF_UP);
        List list = new ArrayList();
        for(int i = 0; i< 10;i++){
            list.add(i);
        }
        list = list.subList(1,3);


        A a = new A();
        B b = new B();
        a.setName("kevin");
        b.setName("kevin1");
        System.out.println(a.getName() + b.getName());

        Period period = Period.between(LocalDate.now(), LocalDate.now());
//    System.out.println(period.getDays());
//    System.out.println(1<<1);

        double pi = 31415926;
        DecimalFormat format = new DecimalFormat("00.00");
        DecimalFormat format1 = new DecimalFormat("圆周率，###米");
        System.out.println(format.format(pi));
        System.out.println(format1.format(pi));
        System.out.println(format1.format(pi).getClass().getName());

        System.out.println(MyDay.MONDAY.getCode());
        System.out.println(MyDay.MONDAY.getName());
        System.out.println(MyDay.MONDAY.toString());
        System.out.println(MyDay.MONDAY);
        ReentrantLock lock = new ReentrantLock(false);
    }
}



