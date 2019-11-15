package com.kevin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kevin
 * @date 2019-11-14 10:05
 * @description todo
 **/
@SpringBootApplication
@EnableTransactionManagement
public class CallBackApplication {
  public static void main(String[] args) {
    SpringApplication.run(CallBackApplication.class,args);
  }
}
