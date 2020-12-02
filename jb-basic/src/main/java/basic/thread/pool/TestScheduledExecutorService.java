package basic.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用newScheduledThreadPool来模拟心跳机制
 */
public class TestScheduledExecutorService
{
	public static void main(String[] args)
	{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		Runnable task = new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("...........TestScheduledExecutorService...........");
			}
		};
		executor.scheduleAtFixedRate(task, 5, 3, TimeUnit.SECONDS);
	}
}
