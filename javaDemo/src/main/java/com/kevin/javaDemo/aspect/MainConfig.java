package com.kevin.javaDemo.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author kevin
 * @date 2020-7-7 16:55
 * @description todo
 **/
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MainConfig {


        @Bean
        public Calculate calculate() {
            return new Calculate();
        }

        @Bean
        public KevinLogAspect kevinLogAspect() {
            return new KevinLogAspect();
        }
}
