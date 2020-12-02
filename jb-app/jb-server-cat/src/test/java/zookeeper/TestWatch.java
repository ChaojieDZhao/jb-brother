package zookeeper;

import constant.AppConstant;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestWatch
{
	static ZkClient zkClient;

	@BeforeClass
	public static void before()
	{
		zkClient = new ZkClient(AppConstant.ZOOKEEPER_CONNECTION_STRING, 3000);
		if (zkClient.exists(AppConstant.strPath))
		{
			zkClient.deleteRecursive(AppConstant.strPath);
		}
		String path = zkClient.create(AppConstant.strPath, AppConstant.I_AM_SUPER_MAN, CreateMode.PERSISTENT);
		System.out.println(path);
	}

	@Test
	public void testUpdate()
		throws InterruptedException
	{
		while (true)
		{
			String data = "逗你玩" + new Random().nextInt(100);
			zkClient.writeData(AppConstant.strPath, data);
			TimeUnit.SECONDS.sleep(5);
			System.out.println(data);
		}
	}

	@Test
	public void testWatchData()
		throws IOException
	{
		zkClient.subscribeDataChanges(AppConstant.strPath, new IZkDataListener()
		{
			public void handleDataDeleted(String path)
				throws Exception
			{
				System.out.println(path + ": i have deleted");
			}

			public void handleDataChange(String path, Object value)
				throws Exception
			{
				System.out.println(path + ": i have changed");
			}
		});
		System.in.read();
	}

	@Test
	public void testWatchChildrenChange()
		throws IOException
	{
		zkClient.subscribeChildChanges(AppConstant.strPath, new IZkChildListener()
		{

			public void handleChildChange(String path, List<String> child)
				throws Exception
			{
				System.out.println(path + ":" + child);
			}
		});
		System.in.read();
	}
}
