package com.kevin;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kevin
 * @date 2019-11-6 13:36
 * @description todo
 **/
public class Exercise {
  public static void main(String[] args) throws Exception {
   Period period=Period.between(LocalDate.now(),LocalDate.now());
//    System.out.println(period.getDays());
//    System.out.println(1<<1);

    double pi=31415926;
      DecimalFormat format=new DecimalFormat("00.00");
      DecimalFormat format1=new DecimalFormat("圆周率，###米");
    System.out.println(format.format(pi));
    System.out.println(format1.format(pi));
    System.out.println(format1.format(pi).getClass().getName());

      List list=new ArrayList();
      list.add(0);
      list.add(1);
      list.add(2);
      list.add(3);
    System.out.println(list.get(0)+"====="+list.get(2));
    String str="qeewtwl";
    String s =str.substring(str.indexOf("l")+1);
    System.out.println(s+"======="+s.length());
  }
  }



