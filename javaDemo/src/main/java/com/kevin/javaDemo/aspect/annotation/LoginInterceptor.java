package com.kevin.javaDemo.aspect.annotation;


import com.sun.xml.internal.ws.client.ResponseContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kevin
 * @date 2020-7-9 10:10
 * @description todo
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String className=((HandlerMethod)handler).getBean().getClass().getName();
        String methodName=((HandlerMethod)handler).getMethod().getName();
        //判断需不需要登陆
        Boolean flag=LoginVerifyMapping.get(className+"."+methodName);
        if(flag!=null&&flag){
            //此时需要登陆，判断是否已登录，如果没有登陆则跳转登陆页面,需要带上当前的访问路径，以便登陆
            //后直接跳转到客户想要访问的页面，调高体验度
            //获取请求方式
            String methodname=request.getMethod();
            //获取项目名
            String contextPath=request.getContextPath();
            //获取requestMapping值
            String mapping=request.getServletPath();
            //获取访问路径，项目名加上mapping值
            String requestUri=request.getRequestURI();
            //获取完整的访问路径
            StringBuffer requestUrl=request.getRequestURL();
            return true;

        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
