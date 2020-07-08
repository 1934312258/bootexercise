package com.kevin.javaDemo.aspect.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author kevin
 * @date 2020-7-8 15:54
 * @description 切面的具体配置放在applicationContext.xml中，该代码取自DanamicDataSource项目
 **/
public class DynamicDataSourceAspect {
    public void pointCut(){}

    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        //拿到目标对象
        Object target=joinPoint.getTarget();
        //拿到执行的方法
        String methodName=joinPoint.getSignature().getName();
        //拿到执行的接口
        Class<?>[]clazz=target.getClass().getInterfaces();
        //拿到执行的参数
        Class<?>[]parameterTypes=((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        //根据方法名和参数，返回当前Method对象
        Method method=clazz[0].getMethod(methodName,parameterTypes);
        //如果方法不等于null，并且方法上添加了@DataSource注解
        if(method !=null && method.isAnnotationPresent(DataSource.class)){
            //获取@DataSource注解对象
            DataSource data=method.getAnnotation(DataSource.class);
            //根据@DataSource注解对象，获取注解信息data.value() 就是你指定的数据源类型的值
            data.value();//将获取到的值赋值给数据源持有器holder，进行具体数据源选择
        }
    }
    public void  after(JoinPoint point){
       //清空持有器的值
        //DynamicDataSourceHolder.clearDataSource();
    }

}
