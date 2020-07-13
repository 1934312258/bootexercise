package com.kevin.javaDemo.InterceptorDemo;

import com.kevin.javaDemo.aspect.annotation.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private TimeInterceptor timeInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截所有对应路径下的请求
        registry.addInterceptor(timeInterceptor).addPathPatterns("/user/*");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/test/*");
    }
}
