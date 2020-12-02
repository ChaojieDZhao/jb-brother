package nio;

import java.util.List;
import java.util.Random;

/**
 * Balancer 上午9:12:19
 * <p>
 * Copyright zhaocj Inc. All rights reserved. Love ME Like Justin Bieber.
 */
public class Balancer
{
	public static String getOneIPAndPort(List<String> hostAndPorts)
	{
		if (hostAndPorts.size() > 0)
		{
			int index = new Random().nextInt(hostAndPorts.size());
			return hostAndPorts.get(index);
		}
		return null;
	}
}
