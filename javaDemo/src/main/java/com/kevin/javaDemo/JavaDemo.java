package com.kevin.javaDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author kevin
 * @date 2019-9-17 15:54
 * @description todo
 **/
//@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JavaDemo {
    public static void main(String[] args) {
        SpringApplication.run(JavaDemo.class, args);
    }
}
