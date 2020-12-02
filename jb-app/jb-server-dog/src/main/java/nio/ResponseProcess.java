package nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResponseProcess
{
	private static ExecutorService executorService = Executors.newFixedThreadPool(50);

	public static void processResponse(final SelectionKey key, Selector selector)
		throws IOException
	{
		executorService.submit(new Runnable()
		{
			public void run()
			{
				try
				{
					SocketChannel sc = (SocketChannel)key.channel();
					ByteArrayOutputStream bos = (ByteArrayOutputStream)key.attachment();
					bos.write(" Server dog node".getBytes());
					ByteBuffer buffer = ByteBuffer.allocate(bos.size());
					buffer.put(bos.toByteArray());
					buffer.flip();
					sc.write(buffer);
					sc.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					try
					{
						key.channel().close();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
