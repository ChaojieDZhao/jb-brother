package basic.thread.example.FutureTask;

import java.util.concurrent.*;

/**
 * @describe
 */
public class FutureCallDemo01
{
	public static void main(String args[])
		throws Exception
	{
		Task01 task = new Task01();
		ExecutorService excutor = Executors.newCachedThreadPool();
		Future<Long> result = excutor.submit(task);
		excutor.shutdown();
		Thread.sleep(1000);
		System.out.println("主线程正在执行任务");
		// 超时的haul抛出TimeoutException
		System.out.println("task运行结果为:" + result.get(10000, TimeUnit.MILLISECONDS));
		System.out.println("task运行结果为:" + result.get(1000, TimeUnit.MILLISECONDS));
	}
}

class Task01 implements Callable<Long>
{
	@Override
	public Long call()
		throws Exception
	{
		Thread.sleep(3000);
		long sum = 1;
		for (int i = 1; i <= 10; i++)
		{
			sum = sum * i;
		}
		return sum;
	}
}
