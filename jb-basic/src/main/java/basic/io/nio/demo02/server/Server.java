package basic.io.nio.demo02.server;

public class Server
{
	private static int DEFAULT_PORT = 12345;

	private static ServerHandler serverHandle;

	public static void start()
	{
		start(DEFAULT_PORT);
	}

	public static synchronized void start(int port)
	{
		if (serverHandle != null)
			serverHandle.stop();
		serverHandle = new ServerHandler(port);
		new Thread(serverHandle, "THREAD-SERVER").start();
	}

	public static void main(String[] args)
	{
		start();
	}
}
