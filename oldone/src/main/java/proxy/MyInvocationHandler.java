package proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;

//动态代理
public class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("MyInvocationHandler invoke begin");

        System.out.println("proxy:  " + proxy.getClass().getName());

        System.out.println("method: " + method.getName());

        for (Object o : args) {
            System.out.println("args" + o);
        }
        //通过反射调用 被代理类的方法
        method.invoke(obj, args);
        System.out.println("MyInvocationHandler invoke end");


        return null;
    }

    public static void main(String[] args) throws IOException {
        //创建被代理的对象
        Student s = new Student();
        //这一句时生成代理类的.class文件 要求建立com.sun.proxy   目的是生成的字节码文件就存放在这里
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获得加载被代理类的类加载器
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        //指明一下被代理类实现的接口
        Class<?>[] interfacesclass = s.getClass().getInterfaces();
        //创建被代理类的委托类，想要调用被代理类的方法时，都会委托给这个类的invoke方法
        MyInvocationHandler handler = new MyInvocationHandler(s);
        //生成代理类
        Person proxy = (Person) Proxy.newProxyInstance(loader, interfacesclass, handler);
        //通过代理类调用 被代理类的方法
        proxy.sayhello("动态代理", 23);
        getproxyclass();
    }

    public static void getproxyclass() throws IOException {
        byte[] bytes = ProxyGenerator.generateProxyClass("Person$Proxy", new Class[]{
                Person.class
        });
        String fileName = System.getProperty("user.dir") + "/src/Person$Proxy.class";
        File file = new File(fileName);
        Files.write(file.toPath(), bytes);
        System.out.println(System.getProperty("user.dir"));
    }
}
