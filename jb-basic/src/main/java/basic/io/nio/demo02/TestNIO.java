package basic.io.nio.demo02;

import basic.io.nio.demo02.client.Client;
import basic.io.nio.demo02.server.Server;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestNIO
{
	//测试主方法
	public static void main(String[] args)
		throws Exception
	{
		//运行服务器
		Server.start();
		//避免客户端先于服务器启动前执行代码
		Thread.sleep(100);
		char operators[] = {'+', '-', '*', '/'};
		Random random = new Random(System.currentTimeMillis());
		//运行客户端
		for (int i = 0; i < 10; i++)
		{
			System.out.println("NUM-" + i + "次请求");
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					String expression =
						random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
					try
					{
						Client.sendMsg(expression);
						TimeUnit.SECONDS.sleep(2L);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}).start();
		}

	}
}
