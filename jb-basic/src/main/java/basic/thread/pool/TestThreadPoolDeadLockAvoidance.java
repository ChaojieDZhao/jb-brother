package basic.thread.pool;

import java.util.concurrent.*;

public class TestThreadPoolDeadLockAvoidance
{
	private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
		1,
		1,
		60L, TimeUnit.SECONDS,
		new SynchronousQueue<Runnable>(),
		new ThreadPoolExecutor.CallerRunsPolicy()
	);

	public static void main(String[] args)
	{
		TestThreadPoolDeadLockAvoidance poolDeadLockAvoidance = new TestThreadPoolDeadLockAvoidance();
		poolDeadLockAvoidance.test("MOBIN");
	}

	public void test(final String mes)
	{
		Runnable taskA = new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("Executing taskA");
				Runnable tabkB = new Runnable()
				{
					@Override
					public void run()
					{
						System.out.println("TaskB processes " + mes);
					}
				};
				Future<?> rs = threadPoolExecutor.submit(tabkB);
				try
				{
					rs.get();   //等待taskB执行
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				catch (ExecutionException e)
				{
					e.printStackTrace();
				}
				System.out.println("TaskA Done");
			}
		};
		threadPoolExecutor.submit(taskA);
	}
}
