package com.kevin;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class jedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        String masterName="mymaster";
        Set<String>sentinels=new HashSet<>();
        sentinels.add(new HostAndPort("192.168.101.15",26379).toString());
        sentinels.add(new HostAndPort("192.168.101.16",26380).toString());
        sentinels.add(new HostAndPort("192.168.101.17",26381).toString());
        sentinels.add(new HostAndPort("192.168.101.18",26382).toString());
        //jedisSentinelPool其本质与JedisPool类似，都是与Redis主节点建立的连接池
        //JedisSentinelPool并不是与sentinel建立的连接池，而是通过sentinel发现Redis主节点并与其建立连接
        JedisSentinelPool jedisSentinelPool=new JedisSentinelPool(masterName,sentinels,config,3000,null);
        Jedis jedis=null;
        try {
            jedis=jedisSentinelPool.getResource();
            System.out.println(jedis.set("kevin","handsome"));
            System.out.println(jedis.get("kevin"));
        }finally {
            if(null!=jedis){
                jedis.close();
            }
        }

    }
}
