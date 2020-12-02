package basic.thread.features;

import java.util.concurrent.CountDownLatch;

/**
 * 羊群抢夺
 */
public class CountDownLatchTest
{
	static CountDownLatch c = new CountDownLatch(2);

	public static void main(String[] args)
		throws InterruptedException
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				System.out.println(1);
				c.countDown();
				System.out.println(2);
				c.countDown();
			}
		}).start();

		c.await();//await会一直阻塞到计数器为零，或者等待中的线程中断，或者等待超时
		System.out.println(3);
	}
}

class CountDownLatchTest01
{
	public static long timeTask(int nThreads, final Runnable task)
		throws InterruptedException
	{
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++)
		{
			Thread t = new Thread("Thread-" + i)
			{
				public void run()
				{
					try
					{
						try
						{
							startGate.await();
						}
						finally
						{
							endGate.countDown();
						}

						task.run();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}

	public static void main(String[] args)
	{
		try
		{
			CountDownLatchTest01.timeTask(10, new TaskRunnable());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}

class TaskRunnable implements Runnable
{
	public void run()
	{
		System.out.println(Thread.currentThread().getName() + "   执行Task！！！");
	}
}
