package com.kevin.javaDemo.FilterDemo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-9-15 18:48
 * @description todo
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter() {
        FilterRegistrationBean<TimeFilter> registrationBean = new FilterRegistrationBean<>();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        //urls.add("/user/*")
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;

    }
}
