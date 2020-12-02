package basic.io.bio.demo03;

import basic.io.utils.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server
{
	private final static Logger LOG = LoggerFactory.getLogger(Server.class);

	//默认的端口号
	private static int DEFAULT_PORT = 12345;

	//单例的ServerSocket
	private static ServerSocket server;

	//线程池，懒汉式的单例
	private static ExecutorService executorService = Executors.newFixedThreadPool(60);

	//根据传入参数设置监听端口，如果没有参数，调用以下方法并使用默认值
	public static void start()
		throws IOException
	{
		//使用默认值
		start(DEFAULT_PORT);
	}

	//这个方法仅用于启动服务器，并不会被大量并发访问，不太需要考虑效率，直接进行方法同步即可
	public synchronized static void start(int port)
		throws IOException
	{
		if (server != null)
			return; //server !=null,说明服务器已经启动，无需再次执行
		try
		{
			/**
			 * 通过构造函数创建ServerSocket
			 * 如果端口合法且空闲，服务端就监听成功
			 */
			server = new ServerSocket(port); //相当于服务器启动了，并且监听了端口port
			LOG.info("服务器已启动，端口号：" + port);
			Socket socket; //网络I/O接口：Socket
			/**
			 * 通过无限循环监听客户端连接，如果没有客户端接入，将阻塞在accept操作上
			 */
			while (true)
			{
				socket = server.accept();
				BufferedReader in = null;
				PrintWriter out = null;
				try
				{
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
					String expression;
					String result;
					if ((expression = in.readLine()) == null)
						break;
					System.out.println("DEMO3 服务器收到信息：" + expression);
					try
					{
						result = Calculator.cal(expression).toString();
						TimeUnit.SECONDS.sleep(10L);
					}
					catch (Exception e)
					{
						result = "计算错误：" + e.getMessage();
					}
					out.println(result);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		finally
		{
			//一些必要的清理工作
			if (server != null)
			{ //
				LOG.info("服务器已关闭。");
				server.close();
				server = null; //无具体含义，只是为了防止内存泄露
			}
		}

	}
}
