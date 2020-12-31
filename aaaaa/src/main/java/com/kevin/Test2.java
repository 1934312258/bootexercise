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
        Boolean flag = null;
        if (flag) {

        }

        test2.setAge(12);
        int qw = -test2.getAge();
        qw = qw + 4;
        System.out.println(qw);
        Random random = new Random();
        int ad = random.nextInt(10);
//      for(;;) {
//          System.out.println(random.nextInt(10));
//      }
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.get("retgfhg"));
        String str = "https://xgimg1test.hktanis.com/data/upload/refund/101280770966984/2020/11/23/102791054396352.png,";
        str = str.substring(str.indexOf("/data/upload"));
        System.out.println(str);
    }
}
