package zookeeper.lock;

import constant.AppConstant;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestLock
{

	RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

	CuratorFramework client = CuratorFrameworkFactory.newClient(AppConstant.ZOOKEEPER_CONNECTION_STRING, retryPolicy);

	InterProcessMutex lock = new InterProcessMutex(client, "/lock/testlock");

	@Test
	public void test1()
		throws Exception
	{
		client.start();
		System.out.println("呵呵");
		if (lock.acquire(1000, TimeUnit.SECONDS))
		{
			try
			{
				System.out.println("您好，我是方法dog");
				Thread.sleep(50000);
			}
			finally
			{
				lock.release();
			}
		}
	}

	@Test
	public void test2()
		throws Exception
	{
		client.start();
		System.out.println("呵呵");
		if (lock.acquire(1000, TimeUnit.SECONDS))
		{
			try
			{
				System.out.println("您好，我是方法dog");
			}
			finally
			{
				lock.release();
			}
		}
	}
}
