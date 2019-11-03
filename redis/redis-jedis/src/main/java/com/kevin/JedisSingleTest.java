package com.kevin;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;

public class JedisSingleTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        //timeout这里既是连接超时又是读写超时，从jedis2.8开始有区分connectionTimeout与soTimeout的构造函数
        JedisPool jedisPool=new JedisPool(jedisPoolConfig,"192.168.101.244",6379,3000,null);
        Jedis jedis=null;
        try {
            jedis = jedisPool.getResource();
            /**管道命令示例*/
            //管道命令的执行方式：cat redis.txt |redis-cli -h 127.0.0.1 -a password -p 6379 --pipe
            Pipeline pl = jedis.pipelined();
            for (int i = 0; i < 10; i++) {
                pl.incr("pipeLinrKey");
                pl.set("kevin" + i, "kevin");
            }
            List<Object> results = pl.syncAndReturnAll();
            System.out.println(results);

            /*lua脚本示例*/
            //模拟一个商品减库存的原子操作
            //lua脚本命令执行的方式：redis-cli --eval/temp/test.lua,10
            jedis.set("product stock 10016", "15");//初始化商品10016的库存
            String script = "local count=redis.call('get',KEYS[1])" +
                    "local a=tonumber(count)" +
                    "local b=tonumber()ARGV[1]" +
                    "if a>=b then" +
                    "redis.call('set',KEYS[1],count -b)" +
                    //模拟语法报错回滚操作 "bb==0"+
                    "return 1" +
                    "end" +
                    "return 0";
            Object object = jedis.eval(script, Arrays.asList("product_stock_10016"), Arrays.asList("10"));
        }finally {
            //这里关闭连接是将jedis连接归还给连接池
           if(jedis!=null){
               jedis.close();
           }
        }



    }
}
