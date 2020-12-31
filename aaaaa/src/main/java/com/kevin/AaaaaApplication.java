package com.kevin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

/**
 * @author kevin
 * @date 2019-11-28 10:14
 * @description todo
 **/
@SpringBootApplication
public class AaaaaApplication {

    public static void main(String[] args) {
        new TestInterface() {
            @Override
            public void test1() {

            }
        };
        BigDecimal b1 = new BigDecimal(2.0);
        BigDecimal b2 = new BigDecimal(2);
        System.out.println(b1.compareTo(b2) >= 0);

        SpringApplication.run(AaaaaApplication.class);

    }
}
