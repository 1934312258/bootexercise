package com.kevin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kevin
 * @date 2019-11-12 19:54
 * @description todo
 **/
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class OrderApplication {
  public static void main(String[] args) {
      SpringApplication.run(OrderApplication.class);
  }
}
