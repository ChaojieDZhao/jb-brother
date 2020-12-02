package zookeeper;

import constant.AppConstant;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestDemo
{
	static ZkClient zkClient;

	@BeforeClass
	public static void before()
	{
		zkClient = new ZkClient(AppConstant.ZOOKEEPER_CONNECTION_STRING);
		if (zkClient.exists(AppConstant.strPath))
		{
			zkClient.deleteRecursive(AppConstant.strPath);
		}
		String path = zkClient.create(AppConstant.strPath, AppConstant.I_AM_SUPER_MAN, CreateMode.PERSISTENT);
		System.out.println(path);
	}

	@AfterClass
	public static void after()
	{
		zkClient = new ZkClient(AppConstant.ZOOKEEPER_CONNECTION_STRING);
		if (zkClient.exists(AppConstant.strPath))
		{
			zkClient.deleteRecursive(AppConstant.strPath);
		}
	}

	@Test
	public void testSave()
	{
		String path = zkClient.create(AppConstant.strPath + "/temp", AppConstant.I_AM_SUPER_MAN, CreateMode.EPHEMERAL);
		System.out.println(path);
	}

	@Test
	public void testGet()
	{
		Object data = zkClient.readData(AppConstant.strPath);
		System.out.println(data.getClass() + " : " + data.toString());
	}

	@Test
	public void testUpdate()
	{
		zkClient.writeData(AppConstant.strPath, "逗你玩111");
	}

	@Test
	public void testCreateChildren()
	{
		zkClient.createPersistent(AppConstant.strPathNode1, true);
		zkClient.createPersistent(AppConstant.strPathNode2, true);
	}

	@Test
	public void testGetChildren()
	{
		List<String> children = zkClient.getChildren(AppConstant.strPath);
		System.out.println(children);
	}
}
