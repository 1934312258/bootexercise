package com.kevin.demo;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @创建人 zhaowenjian
 * @创建时间 2020/12/9
 * @描述
 */
public class StringTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        //timeout这里既是连接超时又是读写超时，从jedis2.8开始有区分connectionTimeout与soTimeout的构造函数
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null);
        Jedis jedis = null;
        jedis = jedisPool.getResource();

        jedis.set("kevin", "kevin01");

    }
}
