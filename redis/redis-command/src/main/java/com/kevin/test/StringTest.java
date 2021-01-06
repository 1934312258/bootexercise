package com.kevin.test;

import com.kevin.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @创建人 zhaowenjian
 * @创建时间 2020/12/9
 * @描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {
    // 必须使用@Resource注解根据bean名称装配，springboot自动注入了RedisTemplate与StringRedisTemplate两个bean，后者直接继承前者
    // 使用autowired注解按类型装配会找到两个类型相同的bean导致注入失败，在配置文件中配置RedisTemplate是为了改变其序列化方式
    @Resource(name = "redisTemplate")
    RedisTemplate template;

    // 此处使用的是RedisTemplate内的实例变量，无需在配置文件配置，序列化方式使用的当前RedisTemplate的序列化方式
    @Resource(name = "redisTemplate")
    static ValueOperations<String, Object> ops;

    @Test
    public void string() {
        ops.set("kevin", "3254354");
    }

}
