package nio;

import constant.AppConstant;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class TestClient
{
	private final static Logger logger = LoggerFactory.getLogger(TestClient.class);

	private final String servername = "/rpcserver/nodes";

	private List<String> hostAndPorts;

	private ZkClient client = new ZkClient(AppConstant.ZOOKEEPER_CONNECTION_STRING);

	public static void main(String[] args)
		throws Exception
	{
		TestClient client = new TestClient();
		client.suscribleHosts();
		while (true)
		{
			Thread.sleep(1000);
			String oneIPAndPort = Balancer.getOneIPAndPort(client.getHostAndPorts());
			logger.info("当前筛选出的可用服务是：{}", oneIPAndPort);
			if (oneIPAndPort == null)
			{
				logger.error("出错了，全部宕机，没有可用服务。。");
			}
			else
			{
				String host = oneIPAndPort.split(":")[0];
				Integer port = Integer.parseInt(oneIPAndPort.split(":")[1]);
				String res = (String)NIOClient.sendCommand(host, port, "呵呵：" + new Date().getTime());
				System.out.println(res);
			}
		}

	}

	// 订阅zookeeper的一个节点目录
	public void suscribleHosts()
	{
		client.subscribeChildChanges(servername, new IZkChildListener()
		{
			public void handleChildChange(String path, List<String> hps)
				throws Exception
			{
				hostAndPorts = hps;
			}
		});
	}

	public List<String> getHostAndPorts()
	{
		if (hostAndPorts == null)
		{
			hostAndPorts = client.getChildren(servername);
			if (hostAndPorts == null || hostAndPorts.size() == 0)
			{
				throw new RuntimeException("没有可用服务....");
			}
			else
			{
				return hostAndPorts;
			}
		}
		else
		{
			return hostAndPorts;
		}
	}
}
