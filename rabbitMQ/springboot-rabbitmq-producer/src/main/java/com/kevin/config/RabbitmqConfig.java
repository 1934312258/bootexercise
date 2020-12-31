package com.kevin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-11-18 8:19
 * @description todo
 **/
@Configuration
public class RabbitmqConfig {
    @Bean
    public DirectExchange springbootDirectExchange() {
        DirectExchange directExchange = new DirectExchange("springboot.direct.exchange", true, false);
        return directExchange;
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayExchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue springbootQueue() {
        Queue queue = new Queue("springbootQueue", true, false, false);
        return queue;
    }

    @Bean
    public Queue clusterQueue() {
        Queue queue = new Queue("clusterQueue", true, false, false);
        return queue;
    }

    @Bean
    public Queue springbootDelayQueue() {
        Queue queue = new Queue("springbootDelayQueue", true, false, false);
        return queue;
    }

    @Bean
    public Binding springbootBind() {
        return BindingBuilder.bind(springbootQueue()).to(springbootDirectExchange()).with("springboot.key");
    }

    @Bean
    public Binding clusterBind() {
        return BindingBuilder.bind(clusterQueue()).to(springbootDirectExchange()).with("rabbitmq.cluster.key");
    }

    @Bean
    public Binding delayBind() {
        return BindingBuilder.bind(springbootDelayQueue()).to(delayExchange()).with("springboot.delay.key").noargs();
    }
}
