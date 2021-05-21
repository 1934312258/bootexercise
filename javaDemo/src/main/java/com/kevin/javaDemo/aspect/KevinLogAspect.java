package com.kevin.javaDemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author kevin
 * @date 2020-7-7 9:41
 * @description todo
 **/

/**
 * 切点表达式
 * 切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])
 * 其中[]内的是可选的，其它的还支持通配符的使用：
 * 1、*：匹配所有字符
 * 2、..：一般用于匹配多个包，多个参数
 * 3、+：表示类及其子类
 * 4、运算符有：&&，||，！
 * <p>
 * 切点表达式关键词用例
 * 1、execution用于匹配子表达式
 * 匹配com.cjm.model包及其子包中所有类中的所有方法，返回类型任意，方法参数任意
 *
 * @Pointcut(“execution(* com.cjm.model...(..))”)
 * public void before(){}
 * <p>
 * 2、within 用于匹配连接点所在的Java类或者包
 * 匹配Person类中的所有方法
 * @Pointcut(“within(com.cjm.model.Person)”) public void before(){}
 * 匹配com.cjm包及其子包中所有类中的所有方法
 * @Pointcut(“within(com.cjm..*)”) public void before(){}
 * <p>
 * 3、this：用于向通知方法中传入代理对象的引用
 * @Before(“before() && this(proxy)”)
 * public void beforeAdvide(JoinPoint point, Object proxy){
 * <p>
 * 4、target：用于向通知方法中传入目标对象的引用
 * @Before(“before() && target(target)
 * public void beforeAdvide(JoinPoint point, Object proxy){
 * <p>
 * 5、args：用于将参数传递到通知方法中
 * @Before(“before() && args(age,username)”)
 * public void beforeAdvide(JoinPoint point, int age, String username){
 * <p>
 * 6、@within：用于匹配在类一级使用了参数确定的注解的类，其所有的方法都将被匹配
 * @Pointcut(“@within(com.cjm.annotation.AdviceAnnotation)”) 所有被@AdviceAnnotation标注的类都将匹配
 * <p>
 * 7、@target：与@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME
 * @Pointcut(“@target(com.cjm.annotation.AdviceAnnotation)”) 8、@args： 传入连接点的对象对应的Java类必须被@args指定的Annotation注解标注。
 * @Before(“@args(com.cjm.annotation.AdviceAnnotation)”) public void beforeAdvide(JoinPoint point){
 * <p>
 * 9、@annotation：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。
 * @Pointcut(“@annotation(com.cjm.annotation.AdviceAnnotation)”) 10、bean：通过受管Bean的名字来限定连接点所在的Bean。该关键词是Spring2.5新增的
 * @Pointcut(“bean(person)”)
 **/
@Aspect
public class KevinLogAspect {
    @Pointcut("execution(* com.kevin.javaDemo.aspect.KevinCalculate.*(..))")
    public void pointCut() {
    }

    ;

    /**
     * @param
     * @return {@link } with
     * @throws
     * @method
     * @description todo
     * @author zhaowenjian
     * @date 2020-7-7 10:31
     * @version 1.0
     */
    //使用目标方法中的参数
//    @Before("execution(* findById*(..)) &&" + "args(id,..)")
//    public void twiceAsOld1(Long id){
//        System.err.println ("切面before执行了。。。。id==" + id);
//    }
    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinpoint) {
        //获取目标方法的参数信息
        Object[] objects = joinpoint.getArgs();
        //获取代理类信息
        Object thi = joinpoint.getThis();
        //获取代理的目标对象
        Object target = joinpoint.getTarget();
        //通知的签名
        Signature signature = joinpoint.getSignature();
        //被代理的方法
        String methodName = signature.getName();
        //被代理类的名字com.kevin.javaDemo.aspect.KevinCalculate
        String className = signature.getDeclaringTypeName();
        //aop被代理类的类信息com.kevin.javaDemo.aspect.KevinCalculate
        Class clas = signature.getDeclaringType();
        //获取RequestAttributes
//        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
//        //从RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request= (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        //获取session
//        HttpSession session= (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        //获取请求参数
//        Enumeration<String>enumeration=request.getParameterNames();
        System.out.println("执行目标方法【" + methodName + "】之前执行<前置通知，入参>" + Arrays.asList(joinpoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之前执行<后置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void methodReturning(JoinPoint joinPoint, Object result) {
        System.out.println(result);
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之前执行<返回通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "pointCut()")
    public void methodAfterThrowing(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之前执行<异常通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

//    @Around(value="pointCut()")
//    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object object=joinPoint.proceed();
//        return object;
//    }

}
