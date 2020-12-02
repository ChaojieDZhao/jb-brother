package nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NIOServer extends Thread
{
	private final static Logger logger = LoggerFactory.getLogger(NIOServer.class);

	private static ServerSocketChannel serverSocketChannel;

	private static List<SelectionKey> wpool = new ArrayList<SelectionKey>();

	private static Selector selector;

	public NIOServer(int port)
	{
		try
		{
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			logger.info("监听在{}端口：", port);
			serverSocketChannel.bind(new InetSocketAddress(port));
			selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void addWriteEvent(SelectionKey key)
	{
		synchronized (wpool)
		{
			wpool.add(wpool.size(), key);
		}
		selector.wakeup();
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				if (selector.select() > 0)
				{
					Set<SelectionKey> selectedKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectedKeys.iterator();
					while (iterator.hasNext())
					{
						SelectionKey key = iterator.next();
						iterator.remove();
						if (key.isAcceptable())
						{
							ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
							SocketChannel sc = ssc.accept();
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_READ);
						}
						else if (key.isReadable())
						{
							key.cancel();
							RequestProcess.processRequest(key);
						}
						else if (key.isWritable())
						{
							key.cancel();
							ResponseProcess.processResponse(key, selector);
						}

					}

				}
				else
				{
					while (!wpool.isEmpty())
					{
						SelectionKey key = wpool.remove(0);
						SocketChannel sc = (SocketChannel)key.channel();
						sc.register(selector, SelectionKey.OP_WRITE, key.attachment());
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
