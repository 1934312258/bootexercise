package com.kevin.javaDemo.InterceptorDemo;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 依赖于web框架，在springMVC中则依赖springMVC框架，在实现上基于Java的反射机制，属于面向切面编程的一种
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=============preHaddle==========");
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        System.out.println();
        request.setAttribute("startTime",new Date().getTime());
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("==================postHand=============");
        Long startTime= (Long) request.getAttribute("starTime");
        System.out.println("time interceptor 耗时"+(new Date().getTime()-startTime));
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("===============afterCompletion===========");
        Long startTime= (Long) request.getAttribute("startTime");
        System.out.println("afterCompletion 耗时==========="+(new Date().getTime()-startTime));
        System.out.println("ex is"+ex);
    }

}
