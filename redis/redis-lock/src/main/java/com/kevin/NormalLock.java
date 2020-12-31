package com.kevin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.LockInfo;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class NormalLock {
    Logger logger = LoggerFactory.getLogger(NormalLock.class);
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "product_001";
        String clientId = UUID.randomUUID().toString();
        try {
            Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, "kevin");//相当于jedis.setnx()
            redisTemplate.expire(lockKey, 30, TimeUnit.SECONDS);
            //上面的用法为两个原子操作，存在事务问题，下面将两个原子操作合并为一个原子操作
            //当一个线程超时仍未走完逻辑流程，此时锁失效，其他线程可以获取锁，当前线程执行完逻辑后删除的锁是其他线程的
            //此时应该给每把锁设置唯一值，每个线程只能删除自己的锁，可以使用UUID或者自定义格式
            redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if (!result) {
                return "1001";
            }
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存为：" + realStock);
                logger.info("扣减成功，剩余库存为{}" + realStock);
            } else {
                System.out.println("扣减库存失败");
            }
        } finally {
            if (clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }
        return "";
    }
}
