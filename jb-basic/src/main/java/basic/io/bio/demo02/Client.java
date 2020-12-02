package basic.io.bio.demo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @describe
 */
public class Client
{
	//默认的端口号
	private static int DEFAULT_SERVER_PORT = 12345;

	private static String DEFAULT_SERVER_IP = "127.0.0.1";

	public static void send(String expression)
	{
		System.out.println("算术表达式为：" + expression);
		BufferedReader in = null;
		Socket socket = null;
		PrintWriter out = null;
		try
		{
			socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
			socket.setSoTimeout(2000);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			for (int i = 0; i < 10; i++)
			{
				out.println(expression);
				//如果没有数据过来，直接报错，如果要维持不报错，则需要一直有数据过来。
				System.out.println("客户端收到结果为：" + in.readLine());
				TimeUnit.SECONDS.sleep(10L);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//执行finally时，说明以上的程序已经运行完成，，则进行一些必要的清理工作
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				in = null;
			}
			if (out != null)
			{
				out.close();
				out = null;
			}
			if (socket != null)
			{
				try
				{
					socket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
