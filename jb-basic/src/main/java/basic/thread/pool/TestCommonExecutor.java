package basic.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestCommonExecutor
{
	public static void main(String args[])
	{
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<Runnable>(5));
		for (int i = 0; i < 15; i++)
		{
			executor.execute(new Task(i));
			System.out.print("线程池中的线程数" + executor.getPoolSize() + "  ");
			System.out.print("线程池中的等待的线程数" + executor.getQueue().size() + "  ");
			System.out.print("线程完成的任务数" + executor.getCompletedTaskCount() + "  ");
			System.out.println();
		}
		executor.shutdown();
	}
}

class Task implements Runnable
{
	private int taskNum;

	public Task(int num)
	{
		this.taskNum = num;
	}

	@Override
	public void run()
	{
		System.out.println("正在执行task " + taskNum);
		try
		{
			TimeUnit.SECONDS.sleep(1L);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("task " + taskNum + "执行完毕");
	}
}