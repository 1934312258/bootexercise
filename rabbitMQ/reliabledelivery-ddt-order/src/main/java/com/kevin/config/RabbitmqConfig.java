package com.kevin.config;

import com.kevin.constants.MqConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kevin
 * @date 2019-11-12 21:54
 * @description todo
 **/
@Configuration
public class RabbitmqConfig {
    @Bean
    public DirectExchange orderToProductExchange() {
        DirectExchange directExchange = new DirectExchange(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME, true, false);
        return directExchange;
    }

    @Bean
    public Queue orderToProductQueue() {
        Queue queue = new Queue(MqConst.ORDER_TO_PRODUCT_QUEUE_NAME, true, false, false);
        return queue;
    }

    @Bean
    public Binding orderToProductBinding() {
        return BindingBuilder.bind(orderToProductQueue()).to(orderToProductExchange()).with(MqConst.ORDER_TO_PRODUCT_ROUTING_KEY);
    }


}
