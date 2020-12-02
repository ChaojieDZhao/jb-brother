package tips.balancer.one;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightRoundRobin
{
	private static AtomicInteger pos = new AtomicInteger(1);

	public static String getServer()
	{
		// 重建一个Map，避免服务器的上下线导致的并发问题
		Map<String, Integer> serverMap =
			new HashMap<String, Integer>();
		serverMap.putAll(IpMap.serverWeightMap);

		// 取得Ip地址List
		Set<Map.Entry<String, Integer>> entries = serverMap.entrySet();
		Iterator<Map.Entry<String, Integer>> entrySet = entries.iterator();
		List<String> serverList = Lists.newArrayList();
		while (entrySet.hasNext())
		{
			Map.Entry<String, Integer> next = entrySet.next();
			for (int i = 0; i < next.getValue(); i++)
				serverList.add(next.getKey());
		}

		String server = null;
		if (pos.get() > entries.size())
			pos.set(0);
		server = serverList.get(pos.get());
		pos.getAndIncrement();
		return server;
	}
}