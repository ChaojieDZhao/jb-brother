package basic.io.nio.MultiReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @describe
 */
public class ServerReactor implements Runnable
{
	//接收的全链接数量
	private static final Integer backlog = 1024;

	private SelectorProvider selectorProvider = SelectorProvider.provider();

	private ServerSocketChannel serverSocketChannel;

	public ServerReactor(int port)
		throws IOException
	{
		serverSocketChannel = selectorProvider.openServerSocketChannel();     //ServerSocketChannel.open();
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress("localhost", port), backlog);
		System.out.println("服务端启动端口：" + port);
		serverSocketChannel.configureBlocking(false);
	}

	@Override
	public void run()
	{
		try
		{
			new ServerDispatcher(serverSocketChannel, SelectorProvider.provider()).execute();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
