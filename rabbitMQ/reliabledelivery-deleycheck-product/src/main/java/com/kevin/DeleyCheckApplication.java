package com.kevin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-11-14 8:52
 * @description todo
 **/
@SpringBootApplication
@EnableTransactionManagement
//@RestController 研究一下此处这个注解的作用
public class DeleyCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeleyCheckApplication.class);
    }
}
