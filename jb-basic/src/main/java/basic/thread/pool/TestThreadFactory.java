package basic.thread.pool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadFactory
{
	private static ExecutorService executorService = new ThreadPoolExecutor(28, 35, 0L, TimeUnit.MILLISECONDS,
		new LinkedBlockingQueue<Runnable>(10), new ThreadFactory()
	{
		final AtomicInteger threadNumber = new AtomicInteger(1);

		@Override
		public Thread newThread(Runnable runnable)
		{
			Thread thread = new Thread(Thread.currentThread().getThreadGroup(), runnable,
				"executor-" + threadNumber.getAndIncrement(), 0); //这里实现命名
			thread.setDaemon(true);
			if (thread.getPriority() != Thread.NORM_PRIORITY)
			{
				thread.setPriority(Thread.NORM_PRIORITY);
			}
			return thread;
		}
	}, new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args)
		throws IOException
	{
		long start = System.currentTimeMillis();
		for (int i = 0; i <= 10000; i++)
		{
			final Integer number = i;
			executorService.submit(new Runnable()
			{
				public void run()
				{
					try
					{
						System.out.println("我是" + Thread.currentThread().getName() + "线程，处理" + number + "请求");
						Thread.sleep(600000);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown(); // 该方法在加入线程队列的线程执行完之前不会执行。
		while (true)
		{
			if (executorService.isTerminated())
			{
				System.out.println("所有的子线程都结束了！");
				long end = System.currentTimeMillis();
				System.out.println("执行时长： " + (end - start));
				break;
			}
		}
	}
}
