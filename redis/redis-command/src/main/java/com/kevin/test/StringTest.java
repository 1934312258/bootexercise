package com.kevin.test;

import com.kevin.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @创建人 zhaowenjian
 * @创建时间 2020/12/9
 * @描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {

    @Autowired
    RedisTemplate template;

    @Autowired
    static ValueOperations<String, Object> ops;

    @Test
    public void string() {
        ops.set("kevin", "3254354");
    }

}
