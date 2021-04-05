package com.kevin.test;

import com.kevin.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nullBitFieldSubCommands;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 zhaowenjian
 * @创建时间 2020/12/9
 * @描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {
    // 必须使用@Resource注解根据bean名称装配，springboot自动注入了RedisTemplate与StringRedisTemplate两个bean，后者直接继承前者
    // 使用autowired注解按类型装配会找到两个类型相同的bean导致注入失败，在配置文件中配置RedisTemplate是为了改变其序列化方式
    @Resource(name = "redisTemplate")
    RedisTemplate template;

    // 此处使用的是RedisTemplate内的实例变量，无需在配置文件配置，序列化方式使用的当前RedisTemplate的序列化方式
    @Resource(name = "redisTemplate")
    static ValueOperations<String, Object> ops;

    @Test
    public void string() {
        ops.set("kevin", "4326534y");
        String Kevin = (String) ops.get("kevin");

        ops.set("kevin","timeout", Duration.ofSeconds(5));
        Kevin = (String) ops.get("kevin");
        ops.set("kevin","timeout", Duration.ofMillis(5000));

        // 没有过期时间，则相当于setNx
        boolean result = ops.setIfAbsent("kevin","setNx");
        result = ops.setIfAbsent("kevin","lock",5, TimeUnit.SECONDS);
        result = ops.setIfAbsent("kevin","lock1",Duration.ofSeconds(5));

        // setIfPresent 只有key存在时才会更新key的value值，如果key不存在并不会添加数据
        result = ops.setIfPresent("kevin1","unknown");
        result = ops.setIfPresent("kevin","unknown1",5,TimeUnit.SECONDS);
        result = ops.setIfPresent("kevin","unknown2",Duration.ofSeconds(5));

        Map<String,Object> map = new HashMap<>();
        map.put("map","map");
        map.put("map1","map1");
        ops.multiSet(map);

        map.put("map2","map2");
        // 只有当所有的key都不存在才会执行，否则均不插入
        result = (boolean) ops.multiSetIfAbsent(map);

        Kevin = (String) ops.getAndSet("kevin","kevin");
        List<String> list = new ArrayList<>();
        list.add("kevin");
        list.add("kevin1");
        List<Object> stringList = ops.multiGet(list);

        /// 自增时如果值为整数，则改变量可以是整数也可以是小数，如果值为非整数（浮点数）则改变值也必须为浮点数,自减时值必须为整数
        Long num = ops.increment("num");
        num = ops.increment("num",10);
        Double du = ops.increment("num",1.234);

        num = ops.decrement("num",6);

        int number = ops.append("kevin","is a good man");

        // 截取存储字符串,偏移量必须为正整数，当key不存在时则在当前设置的value前加偏移量个空格(长度算空格，打印不体现)，如果值存在则在原值的偏移量之后加上新值（如果值的长度小于偏移量则会加空格，打印不展示）
        ops.set("subString","substring",3);
        String substring = ops.get("subString",2,5);
        long size = ops.size("kevin");

        // bigMap 命令  ，Kevin 最外层key，1000，偏移量  ，true
        result = ops.setBit("kevin",1000,true);
        result = ops.getBit("kevin",1000);
        // bitCount 用于统计时如果指定范围，则范围是以字节byte为单位而不是bit位为单位
        int tongji = (int) template.execute((RedisCallback<Long>) con->con.bitCount("kevin".getBytes()));

        //bitfield w get i3 2    i表示有符号数，u表示无符号数，3表示截取三位，2表示从第三位开始截取


        // 有符号数是指获取的位数组中第一个位是符号位，剩下的才是值。如果第一位是1，那就是负数。无符号数表示非负数，没有符号位，获取的位数组全部都是值。有符号数最多可以获取64位，
        // 无符号数只能获取63位 (因为 Redis协议中的integer是有符号数，最大64位，不能传递64位无符号值)
        List<Long> bit = ops.bitField("bigmap", BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.signed(10)).valueAt(0));
        List<Long> bit1 = ops.bitField("bigmap", BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.signed(10)).valueAt(0));
        List<Long> bit2 = ops.bitField("bigmap", BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(10)).valueAt(0));
        List<Long> bit3 = ops.bitField("bigmap", BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(10)).valueAt(10000));
        List<Long> bit4 = ops.bitField("bigmap", BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.signed(8)).valueAt(10));
        List<Long> bit5 = ops.bitField("bigmap", BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.signed(8)).valueAt(10000));
    }

}
