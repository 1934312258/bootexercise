package com.kevin;

import java.util.Hashtable;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author kevin
 * @since 2021/5/8 10:07
 */
public class StaticTest {
    static Map map;

    static {
        map = new Hashtable();
        map.put("name", "kevin");
        map.put("age", 18);
    }


    public static void main(String[] args) {
        String str = "1343355";
        Integer in = Integer.getInteger(str);
        in = Integer.valueOf(str);
        System.out.println(in);
//        long s = System.currentTimeMillis();
//        for(int i =0; i<100000;i++){
//            Thread t = new Thread(()->{
//                System.out.println(map.get("name") + " "+map.get("age"));
//            });
//            t.start();
//        }
//        System.out.println(System.currentTimeMillis() - s);
    }
}



