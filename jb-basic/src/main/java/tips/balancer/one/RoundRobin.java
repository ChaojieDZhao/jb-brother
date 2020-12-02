package tips.balancer.one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobin
{
	private static AtomicInteger pos = new AtomicInteger(1);

	public static String getServer()
	{
		// 重建一个Map，避免服务器的上下线导致的并发问题
		Map<String, Integer> serverMap =
			new HashMap<String, Integer>();
		serverMap.putAll(IpMap.serverWeightMap);

		// 取得Ip地址List
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);

		String server = null;
		if (pos.get() > keySet.size())
			pos.set(0);
		server = keyList.get(pos.getAndIncrement());
		return server;
	}
}