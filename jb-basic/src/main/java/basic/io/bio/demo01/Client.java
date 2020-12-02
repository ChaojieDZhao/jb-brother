package basic.io.bio.demo01;

import basic.io.bio.demo02.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @describe
 */
public class Client
{
	private final static Logger LOG = LoggerFactory.getLogger(Server.class);

	//默认的端口号
	private static int DEFAULT_SERVER_PORT = 12345;

	private static String DEFAULT_SERVER_IP = "127.0.0.1";

	private static Socket socket = null;

	private static BufferedReader in = null;

	private static PrintWriter out = null;

	static
	{
		try
		{
			socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
			socket.setSoTimeout(2000);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void send(String expression)
	{
		send(DEFAULT_SERVER_PORT, expression);
	}

	public static void send(int port, String expression)
	{
		LOG.debug("算术表达式为：" + expression);
		try
		{
			out.println(expression);
			//如果没有数据过来，直接报错，如果要维持不报错，则需要一直有数据过来。
			LOG.debug("客户端收到结果为：" + in.readLine());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			LOG.info("因为使用的一个socket,所以不需要关闭");
		}
	}
}
