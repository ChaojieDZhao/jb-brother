package basic.io.bio.demo01;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class TestBIO
{
	public static void main(String[] args)
		throws InterruptedException
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Server.start();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}, "THREAD-SERVER").start();
		Thread.sleep(100);
		char operators[] = {'+', '-', '*', '/'};
		Random random = new Random(System.currentTimeMillis());
		new Thread(new Runnable()
		{
			@SuppressWarnings("static-access")
			@Override
			public void run()
			{
				while (true)
				{
					String expression =
						random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
					Client.send(expression);
					try
					{
						Thread.currentThread().sleep(random.nextInt(1000) * 10);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

		}, "THREAD-CLIENT").start();
	}

	@Test
	public void test1()
		throws InterruptedException
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Server.start();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}, "THREAD-SERVER").start();
		Thread.sleep(100);
		char operators[] = {'+', '-', '*', '/'};
		Random random = new Random(System.currentTimeMillis());
		new Thread(new Runnable()
		{
			@SuppressWarnings("static-access")
			@Override
			public void run()
			{
				while (true)
				{
					String expression =
						random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
					Client.send(expression);
					try
					{
						Thread.currentThread().sleep(random.nextInt(1000) * 10);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

		}, "THREAD-CLIENT").start();
		Thread.currentThread().join();   //自己阻塞自己
	}
}
