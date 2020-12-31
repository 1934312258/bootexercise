package com.kevin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * @author kevin
 * @date 2019-11-7 11:37
 * @description todo
 **/
public class RedisUtil {
    @Autowired
    StringRedisTemplate template;//验证spring boot项目不用配置是否可以直接装配

    @Autowired
    ValueOperations valueOperations;

    @Autowired
    HashOperations hashOperations;

    @Autowired
    ListOperations listOperations;

    @Autowired
    SetOperations setOperations;

    @Autowired
    ZSetOperations zSetOperations;


    void test1() {
        RedisConnectionUtils.unbindConnection(template.getConnectionFactory());
    }

    public static void main(String[] args) {

    }
}
