package nio;

import constant.AppConstant;
import org.I0Itec.zkclient.ZkClient;

/**
 * ServerRegistryA 上午9:20:39
 * <p>
 * Copyright zhaocj Inc. All rights reserved. Love ME Like Justin Bieber.
 */
public class ServerRegistry
{
	private final String servername = "/rpcserver/nodes";

	private ZkClient client = new ZkClient(AppConstant.ZOOKEEPER_CONNECTION_STRING);

	public static void main(String[] args)
		throws Exception
	{
		ServerRegistry registry = new ServerRegistry();
		registry.registServer();
		//System.in.read();
		//registry.unregistServer();

	}

	public void registServer()
	{
		if (!client.exists(servername))
		{
			client.createPersistent(servername, true);
		}
		String path = servername + "/" + "10.73.158.211:9999";
		if (client.exists(path))
		{
			throw new RuntimeException("有问题，闹鬼");
		}
		else
		{
			client.createEphemeral(path);
		}

	}

	public void unregistServer()
	{
		String path = servername + "/" + "10.73.158.211:9999";
		if (client.exists(path))
		{
			System.out.println("unregistServer:" + path);
			client.deleteRecursive(path);
		}

	}
}
