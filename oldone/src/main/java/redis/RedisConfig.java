package redis;

public class RedisConfig {
    //可用于连接实例的最大个数，默认值为8，如果赋值-1表示不限制
    public static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少状态为idle（空闲的）的jedis实例，默认值也是8
    public static int MAX_IDLE = 200;
    //表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；，-1表示无限制
    public static int MAX_WAIT = 10000;

    public static int timeout = 10000;

    public static int RETRY_NUM = 5;


}
