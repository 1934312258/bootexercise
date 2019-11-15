package com.kevin.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kevin
 * @date 2019-11-15 13:24
 * @description todo
 **/
public class MainStart {
  public static void main(String[] args) {
      AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(RabbitmqConfig.class);
  }
}
