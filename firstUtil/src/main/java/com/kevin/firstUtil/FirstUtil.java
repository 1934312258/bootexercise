package com.kevin.firstUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kevin
 * @date 2019-9-17 15:50
 * @description todo
 **/
@SpringBootApplication
public class FirstUtil {
    @Value("${spring.redis.name}")
    private String redis;
    public static void main(String[] args) {

        SpringApplication.run(FirstUtil.class, args);
    System.out.println(new FirstUtil().redis);
    }
}
