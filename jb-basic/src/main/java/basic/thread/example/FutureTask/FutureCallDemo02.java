package basic.thread.example.FutureTask;

import java.util.concurrent.*;

/**
 * @describe
 */
public class FutureCallDemo02
{

	public static void main(String args[])
		throws Exception
	{
		ExecutorService excutor = Executors.newCachedThreadPool();
		Task02 task = new Task02();
		FutureTask<Long> result = new FutureTask<Long>(task);
		excutor.submit(result);
		Thread.sleep(1000);
		System.out.println("主线程正在执行任务");
		//超时的haul抛出TimeoutException
		System.out.println("task运行结果为:" + result.get(10000, TimeUnit.MILLISECONDS));
	}
}

class Task02 implements Callable<Long>
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