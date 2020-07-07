package com.kevin.javaDemo.InterceptorDemo;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 依赖于web框架，在springMVC中则依赖springMVC框架，在实现上基于Java的反射机制，属于面向切面编程的一种
 * 但我们发现获取不到方法的参数值，这个是为什么呢？在DispatcherServlet类中，方法doDispatch(HttpServletRequest request,
 * HttpServletResponse response)
 * applyPreHandle这个方法执行，就是执行的拦截器的preHandler方法，但这个过程中，controller方法没有从request中获取请求参数，组装方法参数；而是在ha.handle这个方法的时候，才会组装参数
 * 虽然没法得到方法的参数，但是可以获得IOC的bean哦。
 *
 * <p>再说明一点的是postHandler方法
 *
 * <p>postHandler方法的执行，当controller内部有异常，posthandler方法是不会执行的。
 *
 * <p>afterCompletion方法，不管controller内部是否有异常，都会执行此方法；此方法还会有个Exception ex这个参数；如果有异常，ex会有异常值；没有异常
 * 此值为null
 *
 * <p>注意点如果controller内部有异常，但异常被@ControllerAdvice 异常统一捕获的话，ex也会为null
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=============preHaddle==========");
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        System.out.println();
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("==================postHand=============");
        Long startTime= (Long) request.getAttribute("starTime");
        System.out.println("time interceptor 耗时"+(System.currentTimeMillis()-startTime));
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("===============afterCompletion===========");
        Long startTime= (Long) request.getAttribute("startTime");
        System.out.println("afterCompletion 耗时==========="+(System.currentTimeMillis()-startTime));
        System.out.println("ex is"+ex);
    }

}
