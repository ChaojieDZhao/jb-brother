package tips.balancer.one;

import com.google.common.collect.Lists;

import java.util.Random;
import java.util.*;

public class WeightRandom
{
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

		Random random = new Random();
		int randomPos = random.nextInt(serverList.size());

		return serverList.get(randomPos);
	}
}