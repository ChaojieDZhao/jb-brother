package basic.thread.state;

import java.io.IOException;

public class TestBLOCK
{
	public static void main(String[] args)
		throws IOException
	{
		final Object lock = new Object();
		Runnable run = new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < Integer.MAX_VALUE; i++)
				{

					synchronized (lock)
					{
						System.out.println(i);
					}

				}
			}
		};
		Thread t1 = new Thread(run, "THREAD-A");
		Thread t2 = new Thread(run, "THREAD-B");
		t1.start();
		t2.start();
	}
}
