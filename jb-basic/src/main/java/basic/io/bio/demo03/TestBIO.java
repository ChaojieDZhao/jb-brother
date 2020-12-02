package basic.io.bio.demo03;

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
		for (int i = 1; i <= 10; i++)
		{
			System.out.println("NUM-" + i + "次请求");
			char operators[] = {'+', '-', '*', '/'};
			Random random = new Random(System.currentTimeMillis());
			String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					Client.send(expression);
				}
			}, "THREAD-CLIENT" + i).start();
		}
	}

}
