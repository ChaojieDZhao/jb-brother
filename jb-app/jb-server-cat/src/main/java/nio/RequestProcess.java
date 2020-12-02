package nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RequestProcess 上午9:15:15
 * <p>
 * Copyright zhaocj Inc. All rights reserved.
 * Love ME Like Justin Bieber.
 */
public class RequestProcess
{
	private static ExecutorService executorService = Executors.newFixedThreadPool(50);

	public static void processRequest(final SelectionKey key)
		throws IOException
	{
		executorService.submit(new Runnable()
		{
			public void run()
			{
				try
				{
					SocketChannel sc = (SocketChannel)key.channel();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					int len = -1;
					while (true)
					{
						buffer.clear();
						len = sc.read(buffer);
						if (len == -1)
						{
							break;
						}
						buffer.flip();
						bos.write(buffer.array(), 0, len);
					}
					key.attach(bos);
					NIOServer.addWriteEvent(key);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
