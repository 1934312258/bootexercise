package com.kevin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @date 2019-11-4 15:45
 * @description todo
 **/
public class ProductServiceLock {
    private Logger logger= LoggerFactory.getLogger(ProductServiceLock.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    //锁失效时间
    private long lockTimeOut=1000*10L;
    //获取锁等待时间
    private long lockWaitTime =1000*10L;
    //休眠时长
    private long sleeptime=1000L;
    //当前时间
    private long currentLockTime;

    public boolean lock(String key,int lockTimeOut) throws Exception {
        if(lockTimeOut!=0&&!"".equals(lockTimeOut)){
            this.lockTimeOut=lockTimeOut;
        }
        if(StringUtils.isEmpty(key)){
            throw new Exception("分布式锁为空");
        }
        try{
            currentLockTime=System.currentTimeMillis();
            long lockWaitTimeOut=lockWaitTime+currentLockTime;
            while(true){
               currentLockTime=System.currentTimeMillis();
               if(lockWaitTimeOut<currentLockTime){
                   break;
               }
               Boolean islock=redisTemplate.opsForValue().setIfAbsent(key,currentLockTime+"",lockTimeOut, TimeUnit.SECONDS);
               if(islock){
                   logger.info(String.format("直接获取锁key:%s,当前时间:%s",key,currentLockTime));
                   return true;
               }else{
                   //锁被占用
                   logger.info(String.format("检测到锁被占用key:%s,当前时间:%s",key,currentLockTime));
                   String otherLockTime=redisTemplate.opsForValue().get(key);
                   if(StringUtils.isEmpty(otherLockTime)){
                      //锁已经被释放，立即重新尝试获取锁
                      continue;
                   }else if(currentLockTime-Long.valueOf(otherLockTime)>=lockTimeOut){
                       //锁超时，尝试获取锁
                        if(otherLockTime.equals(redisTemplate.opsForValue().get(key))){
                            redisTemplate.delete(key);
                            continue;
                        }else{
                            sleep();
                            continue;
                        }
                   }else{
                      sleep();
                      continue;
                   }
               }
            }
        }finally{

        }
        return false;
    }
    private void sleep(){
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            logger.info("线程中断异常"+e.getMessage()+e);
        }
    }
}
