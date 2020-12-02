package basic.thread.state;

import java.io.IOException;

public class TestTIMEDWAITING
{
	public static void main(String[] args)
		throws IOException, InterruptedException
	{
		final Object lock = new Object();
		Thread t1 = new Thread("THREAD-A")
		{
			@Override
			public void run()
			{
				int i = 0;
				while (true)
				{
					synchronized (lock)
					{
						try
						{
							System.out.println("调用等待方法");
							lock.wait(60 * 1000L);
						}
						catch (InterruptedException e)
						{
						}
						System.out.println(i++);
					}
				}
			}
		};
		Thread t2 = new Thread("THREAD-B")
		{
			@Override
			public void run()
			{

				while (true)
				{
					synchronized (lock)
					{
						for (int i = 0; i < 10000000; i++)
						{
							System.out.println(i);
						}
						lock.notifyAll();
					}
				}
			}
		};
		//先让线程一先执行
		t1.start();
		Thread.sleep(15000);
		t2.start();
	}
}
