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
        do{
            System.out.println("begin");
        }while(1>2);

        System.out.println("end");

    }
}



