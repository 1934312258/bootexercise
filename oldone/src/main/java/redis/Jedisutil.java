package redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

public class Jedisutil {
	private Logger logger = LoggerFactory.getLogger(Jedisutil.class);
	private Jedisutil(){
	}
	
	private static class RedisUtilHolder{
		private static final Jedisutil instance=new Jedisutil();
	}
	
	public static Jedisutil getInstance(){
		return RedisUtilHolder.instance;
	}
	
	private static Map<String,JedisPool> maps=new HashMap<>();
	
	private static JedisPool getPool(String ip,int port){
		String key=ip+":"+port;
		JedisPool pool=null;
		if(!maps.containsKey(key)){
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxIdle(RedisConfig.MAX_IDLE);
			//testOnBorrow：在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的
			config.setTestOnBorrow(true);
			//在return给pool时，是否提前进行validate操作
			//每次取jedis时，都会测试jedis.isConnected和ping一下服务端，但这样会造成redis的压力，testOnBorrow和testOnReturn在生产环境一般是不开启的
			config.setTestOnReturn(true);
			pool=new JedisPool(config,ip,port,RedisConfig.timeout);
			maps.put(key, pool);
		}else{
			pool=maps.get(key);
		}
		return pool;
		
	}
	
	public Jedis getJedis(String ip,int port){
		Jedis jedis=null;
		int count=0;
		do{
			try{
				jedis=getPool(ip, port).getResource();
				count++;
			}catch(Exception e){
				e.printStackTrace();
				logger.error("get redis masterl failed!",e);
				//移除等待队列里的异常连接
				getPool(ip, port).returnBrokenResource(jedis);
			}
		}
		while(jedis==null&&count<RedisConfig.RETRY_NUM);
		return jedis;
		
	}
	
	public void closeJedis(Jedis jedis,String ip,int port){
		if(jedis !=null){
			//回收连接
			getPool(ip, port).returnResource(jedis);
		}
	}
}
