package com.kevin;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class);
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        //单机
        config.useSingleServer().setAddress("redis://192.168.101.19:6379").setDatabase(0);
        //集群
        config.useClusterServers()
                .addNodeAddress("192.168.101.18:9001")
                .addNodeAddress("192.168.101.18:9001")
                .addNodeAddress("192.168.101.18:9001")
                .addNodeAddress("192.168.101.18:9001")
                .addNodeAddress("192.168.101.18:9001")
                .addNodeAddress("192.168.101.18:9001");
        return (Redisson) Redisson.create(config);
    }
}
