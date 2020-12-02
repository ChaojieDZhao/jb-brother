package basic.io.aio.server;

public class Server
{
	/**
	 * 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最新的值。
	 * volatile很容易被误用，用来进行原子性操作
	 */
	public volatile static long clientCount = 0;

	private static int DEFAULT_PORT = 12345;

	private static AsyncServerHandler serverHandler;

	public static void start()
	{
		start(DEFAULT_PORT);
	}

	public static synchronized void start(int port)
	{
		if (serverHandler != null)
		{
			return;
		}
		serverHandler = new AsyncServerHandler(port);
		new Thread(serverHandler, "Server").start();//
	}

	public static void main(String[] args)
	{
		Server.start();
	}
}
