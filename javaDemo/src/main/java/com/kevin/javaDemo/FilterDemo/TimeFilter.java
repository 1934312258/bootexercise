package com.kevin.javaDemo.FilterDemo;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author kevin
 * @date 2019-9-15 18:38
 * @description TimeFilter需要配置生成bean对象交由spring管理
 * 两种方案1、在filter类上使用注解@Componet
 * 2、使用config配置类，@Configuration修饰配置类，此方法可以细化到具体过滤哪些规则的URL
 * <p>
 * filter中无法使用注入的bean，既无法使用@Autowired注解，在spring中，web应用的启动顺序是listener->filter->servelt
 * 首先初始化listener然后初始化filter，然后才是dispathServelt的初始化，因此当filter里注入一个注解的bean时，就会注入失败
 * 因为filter初始化时，注解的bean还没初始化
 **/
public class TimeFilter implements Filter {
    UserInfo userInfo = new UserInfo();


    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("=========time filter init");
        if (userInfo != null) {
            System.out.println("-------------------------------------------------------------------");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("========time filter start==========");
        Long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        Long endTime = System.currentTimeMillis();
        System.out.println("timeFilter 消耗" + (endTime - startTime) + "ms");
        System.out.println("=========time filter end========");
    }

    @Override
    public void destroy() {
        System.out.println("============time filter destroy========");
    }
}
