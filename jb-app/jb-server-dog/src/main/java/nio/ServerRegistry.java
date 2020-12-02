package nio;

import constant.AppConstant;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServerRegistryA 上午9:20:39
 * <p>
 * Copyright zhaocj Inc. All rights reserved. Love ME Like Justin Bieber.
 */
public class ServerRegistry
{
	private final static Logger logger = LoggerFactory.getLogger(ServerRegistry.class);

	private final String servername = "/rpcserver/nodes";

	private ZkClient client = new ZkClient(AppConstant.ZOOKEEPER_CONNECTION_STRING);

	public static void main(String[] args)
		throws Exception
	{
		ServerRegistry registry = new ServerRegistry();
		registry.registServer();
		System.in.read();
		// registryA.unregistServer();
	}

	public void registServer()
	{
		if (!client.exists(servername))
		{
			client.createPersistent(servername, true);
		}
		String path = servername + "/" + "192.168.0.222:8888";
		if (client.exists(path))
		{
			throw new RuntimeException("有问题，闹鬼");
		}
		else
		{
			logger.info("注册一下.....");
			client.createEphemeral(path);
		}

	}

	public void unregistServer()
	{
		String path = servername + "/" + "192.168.0.222:8888";
		if (client.exists(path))
		{
			System.out.println("unregistServer:" + path);
			client.deleteRecursive(path);
		}

	}
}
