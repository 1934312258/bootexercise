package com.kevin.javaDemo.lambda;

import org.junit.jupiter.api.Test;

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

}
