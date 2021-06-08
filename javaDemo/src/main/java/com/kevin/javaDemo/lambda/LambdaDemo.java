package com.kevin.javaDemo.lambda;


import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

/**
 * @author kevin
 * @date 2020-7-2 20:05
 * @description todo
 **/
public class LambdaDemo {
    public interface UserService {
        String getUser(String id);
    }

    public static void proxyGetUser(UserService service) {
        service.getUser("kevin");
    }

    public static String proxy(String str) {
        System.out.println(str);
        return str;
    }

    //consumer
    public static void consumer(String str, Consumer consumer) {
        consumer.accept(str);
    }

    // 调用两次，前一个调用完，后一个调用，两次调用隔离不会相互影响
    public static void consumerTwice(String str, Consumer consumer, Consumer consumer1) {
        consumer.andThen(consumer1).accept(str);
    }

    // supplier
    public static void supplier(int type, Supplier supplier) {
        System.out.println(supplier.get());
    }

    //predicate
    public static boolean predicate(String str, String str1, Predicate<String> predicate) {
        return predicate.test(str1);
    }

    public static boolean orpredicate(String str, String str1, Predicate<String> predicate, Predicate predicate1) {
        return predicate.and(predicate1).test(str);
    }

    public static boolean andpredicate(String str, String str1, Predicate<String> predicate, Predicate predicate1) {
        return predicate.or(predicate1).test(str);
    }

    public static boolean negate(String str, String str1, Predicate<String> predicate) {
        return predicate.negate().test(str);
    }

    // function
    public static boolean function(String str, Function<String, Boolean> function) {
        return function.apply(str);
    }

    // 第一个函数调用完成之后调用第二个函数
    public static boolean andfunction(String str, Function<String, Boolean> function, Function<Boolean, Boolean> function1) {
        return function.andThen(function1).apply(str);
    }

    // 第二个函数先执行，然后执行第一个函数
    public static boolean composefunction(String str, Function<Boolean, Boolean> function, Function<String, Boolean> function1) {
        return function.compose(function1).apply(str);
    }

    // optional


    @Test
    public void Test1() {
        //表达式
        proxyGetUser(a -> null);
        proxyGetUser(new UserService() {
            @Override
            public String getUser(String id) {
                return id;
            }
        });

        // 语句块,可以写多条语句
        proxyGetUser(
                b -> {
                    System.out.println(b);
                    return b;
                });

        //引用  自身对象引用
        proxyGetUser(String::toString);
        //构造方法引用,在new对象的时候将id传进来
        proxyGetUser(String::new);
        //静态方法引用
        proxyGetUser(LambdaDemo::proxy);

    }

    //consumer,用于foreach,此处调用上面的方法，上面方法如果不调用函数接口的方法，则函数式接口不执行
    @Test
    public void consumer() {
        consumer("Kevin", str -> System.out.println(str + "is a good man"));
        consumerTwice("Kevin", str -> System.out.println(str + "is a good man"), str -> System.out.println(str + "is very handsome"));
        Consumer consumer = x -> System.out.println(x);
        consumer.accept(200);
    }

    // suppiler,此处调用上面的方法，上面方法如果不调用函数接口的方法，则函数式接口不执行
    @Test
    public void supplier() {
        supplier(1, () -> {
            return "first";
        });
    }

    // predicate,此处调用上面的方法，上面方法如果不调用函数接口的方法，则函数式接口不执行
    @Test
    public void predicate() {
        String str = "Kevin";
        String str1 = "zhao";
        predicate(str, str1, (String str2) -> {
            return str2.equals(str);
        });
        orpredicate(str, str1, str2 -> str2.equals(str1), str2 -> str2.equals(str));
        andpredicate(str, str1, str2 -> str2.equals(str), str2 -> str2.equals(str));
        negate(str, str1, str2 -> str2.equals(str));
    }

    // function,此处调用上面的方法，上面方法如果不调用函数接口的方法，则函数式接口不执行
    @Test
    public void function() {
        boolean result = function("kevin", str -> str.length() > 2);
        result = andfunction("kevin", str -> str.length() > 2, str -> !str);
        result = composefunction("kevin", str -> str, str -> str.length() > 2);
    }

    @Test
    public void optional() {
        Optional<String> optional = Optional.empty();//空对象
        Optional<String> optional1 = Optional.of("kevin");//值不能为空
        Optional<String> optional2 = Optional.ofNullable(null);//值可以为空
        optional.isPresent();
        optional.ifPresent(a -> System.out.println(a));
        List list = Arrays.asList(1, 2, 3);
        Optional<List> optionalList = Optional.of(list);//空对象
        optionalList.ifPresent(a -> System.out.println(a.size()));
        optionalList.ifPresent(a -> a.forEach(System.out::println));
        optional1.filter(a -> a.length() > 3).ifPresent(System.out::println);
        optional.filter(a -> a.length() > 3).map(a -> a + " is a good man").ifPresent(System.out::println);
        optional.filter(a -> a.length() > 3).flatMap(a -> Optional.ofNullable(a)).ifPresent(System.out::println);
        optional.orElse("zhao");//值不为空则返回当前值，否则返回传递的值
        optional.orElseGet(() -> "kevin");//值不为空则返回当前值，否则执行函数式接口

    }

}
