package basic.thread.state;

import java.io.IOException;

public class TestWAITING
{
	public static void main(String[] args)
		throws IOException, InterruptedException
	{
		final Object lock = new Object();
		Thread t1 = new Thread("waiting-1")
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
							lock.wait();
						}
						catch (InterruptedException e)
						{
						}
						System.out.println(i++);
					}
				}
			}
		};
		Thread t2 = new Thread("waiting-2")
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
		Thread.sleep(5000);
		t2.start();
	}
}
