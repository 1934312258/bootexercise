package com.kevin.javaDemo.threadLocal;

/**
 * <p>
 *
 * </p>
 *
 * @author sunjinyue
 * @since 2021/5/6 14:57
 */
public class People {
    private String name;
    private Integer age;

    static ThreadLocal<People> threadLocal = ThreadLocal.withInitial(() -> new People());

    public static People getPeople() {
        return threadLocal.get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
