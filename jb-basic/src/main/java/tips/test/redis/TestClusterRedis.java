package tips.test.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestClusterRedis
{

	public static void main(String[] args)
		throws IOException
	{
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("10.9.4.206", 7000));
		jedisClusterNode.add(new HostAndPort("10.9.4.206", 7001));
		jedisClusterNode.add(new HostAndPort("10.9.4.206", 7002));
		jedisClusterNode.add(new HostAndPort("10.9.4.207", 7000));
		jedisClusterNode.add(new HostAndPort("10.9.4.207", 7001));
		jedisClusterNode.add(new HostAndPort("10.9.4.207", 7002));
		//GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		//JedisCluster jc = new JedisCluster(jedisClusterNode,2000,100, goConfig);
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(100);
		cfg.setMaxIdle(20);
		cfg.setMaxWaitMillis(-1);
		cfg.setTestOnBorrow(true);
		JedisCluster jc = new JedisCluster(jedisClusterNode, 6000, 1000, cfg);

		System.out.println(jc.set("zcj_age", "20"));
		System.out.println(jc.get("zcj_age"));
		System.out.println(jc.del("zcj_age"));
		jc.del("day:statsRecordSource");
		jc.close();

	}

	@Test
	public void test1()
		throws IOException
	{
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("10.9.4.206", 7000));
		jedisClusterNode.add(new HostAndPort("10.9.4.206", 7001));
		jedisClusterNode.add(new HostAndPort("10.9.4.206", 7002));
		jedisClusterNode.add(new HostAndPort("10.9.4.207", 7000));
		jedisClusterNode.add(new HostAndPort("10.9.4.207", 7001));
		jedisClusterNode.add(new HostAndPort("10.9.4.207", 7002));
		//GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		//JedisCluster jc = new JedisCluster(jedisClusterNode,2000,100, goConfig);
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(100);
		cfg.setMaxIdle(20);
		cfg.setMaxWaitMillis(-1);
		cfg.setTestOnBorrow(true);
		JedisCluster jc = new JedisCluster(jedisClusterNode, 6000, 1000, cfg);

		System.out.println(jc.set("zcj_age", "20"));
		System.out.println(jc.get("zcj_age"));
		System.out.println(jc.del("zcj_age"));
		String s = jc.get("opt_abbreviate_map-京");
		System.out.println("opt_abbreviate_map-" + s);
		jc.close();
	}

}
