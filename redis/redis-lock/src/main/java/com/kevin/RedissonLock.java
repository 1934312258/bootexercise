package com.kevin;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedissonLock {
    Logger logger = LoggerFactory.getLogger(RedissonLock.class);
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    Redisson redisson;

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "product_001";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            //加锁并实现锁续命
            redissonLock.lock();
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
            redissonLock.unlock();
        }
        return "end";
    }
}
