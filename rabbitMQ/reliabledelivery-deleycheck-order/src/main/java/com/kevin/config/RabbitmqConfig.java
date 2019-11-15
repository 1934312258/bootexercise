package com.kevin.config;

import com.kevin.constants.MqConst;
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
 * @date 2019-11-14 13:52
 * @description todo
 **/
@Configuration
public class RabbitmqConfig {

    @Bean
    public DirectExchange orderToProductExchange(){
        DirectExchange directExchange=new DirectExchange(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME,true,false);
        return directExchange;
    }

    @Bean
    public Queue orderToProductQueue(){
        Queue queue=new Queue(MqConst.ORDER_TO_PRODUCT_QUEUE_NAME,true,false,false);
        return queue;
    }

    @Bean
    public Queue productToCallBackQueue(){
        Queue queue=new Queue(MqConst.PRODUCT_TO_CALLBACK_QUEUE_NAME,true,false,false);
        return queue;
    }

    @Bean
    public Binding orderToProductBinding(){
        return BindingBuilder.bind(orderToProductQueue()).to(orderToProductExchange()).with(MqConst.ORDER_TO_PRODUCT_ROUTING_KEY);
    }

    @Bean
    public Binding productToCallBackBinding(){
        return BindingBuilder.bind(productToCallBackQueue()).to(orderToProductExchange()).with(MqConst.PRODUCT_TO_CALLBACK_ROUTING_KEY);
    }

    //声明一个延时交换机
    @Bean
    public CustomExchange delayExchange(){
        Map<String,Object>args=new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange(MqConst.ORDER_TO_PRODUCT_DELAY_EXCHANGE_NAME,"x-delayed-message",true,false,args);
    }

    @Bean
    public Queue delayQueue(){
        Queue queue=new Queue(MqConst.ORDER_TO_PRODUCT_DELAY_QUEUE_NAME,true,false,false);
        return queue;
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(MqConst.ORDER_TO_PRODUCT_DELAY_ROUTING_KEY).noargs();
    }
}
