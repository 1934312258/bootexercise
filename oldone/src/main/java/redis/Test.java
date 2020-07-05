package redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Test {
	private static final String IP="192.168.0.104";
	private static final int port=6379;
	private static Jedis jedis=null;
	
	@BeforeClass
	public static void init(){
		jedis= Jedisutil.getInstance().getJedis(IP, port);
	}
	
	@AfterClass
	public static void close(){
		Jedisutil.getInstance().closeJedis(jedis, IP, port);
	}
	@org.junit.Test
	public void testString(){
		jedis.set("name","123");
		System.out.println(jedis.exists("name"));
	}
	
	@org.junit.Test
	public void testList(){
		jedis.flushAll();
		jedis.lpush("list", "piaoming","kevin","zhaowenjian");
		System.out.println(jedis.lrange("list", 0, -1));
	}
	
	@org.junit.Test
	public void testSet(){
		jedis.sadd("set", "one","two","three","four");
		jedis.sadd("set1", "one","five","six","seven");
		System.out.println(jedis.smembers("set"));
		System.out.println(jedis.sinter("set","set1"));//交集
		System.out.println(jedis.sinter("set","set1"));//并集
		System.out.println(jedis.sdiff("set","set1"));//差集，以set为模板，获取set与set1交集与set的差
	}
	@org.junit.Test
	public void testSortset(){
		Map<String, Double>map=new HashMap<>();
		map.put("january", 1d);
		map.put("february", 2d);
		map.put("march", 3d);
		map.put("april", 4d);
		map.put("may", 5d);
		jedis.zadd("zset", map);
		System.out.println(jedis.zrange("zset", 0, -1));
		System.out.println(jedis.zscore("zset","april"));
		System.out.println(jedis.zrank("zset","january"));//排序为升序，从零开始
}
	@org.junit.Test
	public void testHash(){
		Map<String, String>map=new HashMap<>();
		map.put("monday", "");
		map.put("tuesday", "1");
		map.put("wednesday", "3d");
		map.put("thursday", "4d");
		map.put("friday", "5");
		jedis.hmset("hash", map);
		System.out.println(jedis.hgetAll("hash"));
		System.out.println(jedis.hkeys("hash"));
		System.out.println(jedis.hvals("hash"));//排序为升序，从零开始
}
}