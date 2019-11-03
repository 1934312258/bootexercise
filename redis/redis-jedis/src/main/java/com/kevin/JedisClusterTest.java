package com.kevin;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisClusterTest {
    public static void main(String[] args) throws IOException {
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        Set<HostAndPort>jedisClusterNode=new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.101.15",8001));
        jedisClusterNode.add(new HostAndPort("192.168.101.16",8002));
        jedisClusterNode.add(new HostAndPort("192.168.101.17",8003));
        jedisClusterNode.add(new HostAndPort("192.168.101.18",8004));
        jedisClusterNode.add(new HostAndPort("192.168.101.19",8005));
        jedisClusterNode.add(new HostAndPort("192.168.101.20",8006));

        JedisCluster jedisCluster=null;
        try {
            //connectionTimeout:指的是连接一个URL的连接等待时间
            //soTimeout：指的是连接上一个URL，获取response的返回等待时间
            jedisCluster=new JedisCluster(jedisClusterNode,6000,5000,10,"kevin",jedisPoolConfig);
            System.out.println(jedisCluster.set("kevin","handsome"));
            System.out.println(jedisCluster.get("kevin"));

        }catch (Exception e){

        }
        finally {
            if(null!=jedisCluster){
                jedisCluster.close();
            }
        }

    }
}
