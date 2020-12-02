package basic.io.nio.demo02.client;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Client
{
	private static String DEFAULT_HOST = "127.0.0.1";

	private static int DEFAULT_PORT = 12345;

	private static AtomicInteger incr = new AtomicInteger(0);

	//向服务器发送消息
	public static boolean sendMsg(String msg)
		throws Exception
	{
		ClientHandler clientHandle = new ClientHandler(DEFAULT_HOST, DEFAULT_PORT);
		new Thread(clientHandle, "THREAD-CLIENT-" + incr.getAndIncrement()).start();
		TimeUnit.SECONDS.sleep(2L);
		clientHandle.sendMsg(msg);
		return true;
	}
}
