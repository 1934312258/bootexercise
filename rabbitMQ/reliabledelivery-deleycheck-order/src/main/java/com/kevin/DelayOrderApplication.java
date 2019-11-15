package com.kevin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kevin
 * @date 2019-11-14 13:38
 * @description todo
 **/
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class DelayOrderApplication {
  public static void main(String[] args) {
      SpringApplication.run(DelayOrderApplication.class,args);
  }

  /**
   * 1、发送消息到订单队列，与延时队列
   * 2、消费端签收订单消息，并发送消息到 callback队列
   * 3、callback服务确认消息并将订单与消息实体存库，callback服务同时监控延迟队列，收到消息后根据
   * 消息ID查询订单与消息实体，若存在则订单完成，不存在则重新调用接口重试发送消息
   * */
}
