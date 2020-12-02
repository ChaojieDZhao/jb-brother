package basic.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock
{

	public static void init()
	{
		final Outprint outprint = new Outprint();
		Thread thread_A = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					while (true)
					{
						Thread.sleep(2000);
						outprint.out("hadoop");
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}, "Thread-A"
		);
		thread_A.setPriority(10);
		thread_A.start();

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					while (true)
					{
						Thread.sleep(2000);
						outprint.out("spark");
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		).start();
	}

	public static void main(String[] args)
	{
		TestReentrantLock.init();
	}

	static class Outprint
	{
		Lock lock = new ReentrantLock();

		public void out(String str)
		{
			lock.lock();
			try
			{
				for (int i = 0; i < str.length(); i++)
				{
					System.out.print(str.charAt(i));
				}
				System.out.println();
				// lock.unlock();//释放锁（如果上面的代码在unlock之前出错，那么锁将不会被释放，所以最好放到finally中）
			}
			finally
			{
				lock.unlock();//释放锁
			}
		}
	}
}
