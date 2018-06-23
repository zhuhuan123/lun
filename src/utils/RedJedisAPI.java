package utils;

import java.util.ArrayList;

import redis.clients.jedis.Jedis;

public class RedJedisAPI {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);		
		String r = jedis.get("foo");
		System.out.println(" key 所对应的值为  "+r);
		Long len = jedis.llen("l2");
	    System.out.println("list的长度为  "+len);
	    Long hl = jedis.hlen("url");
	    System.out.println("hashset 长度 为   "+hl);
	    
	    String re =jedis.get("age");
	    System.out.println("age 的年龄字段值为  "+re);
	    
	}
	
	void j(){
		Jedis jedis = new Jedis("127.0.0.1",6379);
//		String k1 = jedis.get("k1");
//		System.out.println(k1);
		System.out.println(jedis.get("foo"));
		
//		ArrayList<String> arrayList = new ArrayList<>();
//		arrayList.add("aa");
//		arrayList.add("bb");
//		arrayList.add("常常");
		
		
		jedis.lpushx("l2", "l1aa");
//		jedis.lpushx("l1", "l2bb");
//		jedis.lpushx("l1", "l3cc");
		jedis.save();
//		jedis.c

		System.out.println(jedis.get("l2"));
		System.out.println(jedis.llen("l2"));
		
//		jedis.hms
//		jedis.lpush("zhu", "as","dd");
		jedis.rpush("zhu", "csf");
		jedis.save();
		System.out.println(jedis.lrange("zhu", 0, 2));
		
		jedis.hset("url", "goo", "www.google.com");
		jedis.hset("url", "bai", "www.bai.com");
		jedis.save();
//		System.out.println(jedis.getSet("l1",""));		
//		jedis.set("arr", arrayList);
//		jedis.l

	}
}

