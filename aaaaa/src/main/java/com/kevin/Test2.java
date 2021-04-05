package com.kevin;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author
 * @date 2020-5-27 16:56
 * @description todo
 **/
@Data
public class Test2 implements TestInterface {

    private static java.lang.Object Object;
    private String name;
    private Integer age;
    static Object object = null;

    @Override
    public void test1() {
        System.out.println("多个怎么解决");
    }

    private String handleArrayList(List list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(a -> {
            sb.append(a + ",");
        });
        return sb.substring(0, sb.length() - 1);
    }

    static boolean first;

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        A a = (A) Object;

//        test2.setAge(12);
//        int qw = -test2.getAge();
//        qw = qw + 4;
//        System.out.println(qw);
//        Random random = new Random();
//        int ad = random.nextInt(10);
//      for(;;) {
//          System.out.println(random.nextInt(10));
//      }
      /** /a/b/c/ 变成 /a/b/c
                /a//b/ 变成 /a/b
                /a/./../b/../c/ 变成 /c
       */

      String str = "/a/b/c/";
      String str1 = "/a//b/";
      String str2 = "/a/./../b/../c/";
        System.out.println(str.substring(0,str.length()-1));
        System.out.println(str1.substring(0,str1.length()-1).replace("//","/"));
        System.out.println(str2.substring(str2.length()-3,str2.length()-1));
        System.out.println(str2.codePointAt(0));
        System.out.println(str2.codePointAt(1));
        System.out.println(str2.codePointBefore(2));
        System.out.println(str2.codePointCount(0,2));
        String string = new String(new char[2]);
    }

}
