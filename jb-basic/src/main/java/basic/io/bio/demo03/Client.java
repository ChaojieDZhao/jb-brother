package basic.io.bio.demo03;

import basic.io.bio.demo02.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @describe
 */
public class Client
{
	private final static Logger LOG = LoggerFactory.getLogger(Server.class);

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
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(expression);
			//如果没有数据过来，直接报错，如果要维持不报错，则需要一直有数据过来。
			System.out.println("客户端收到结果为：" + in.readLine());
			TimeUnit.SECONDS.sleep(4L);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
