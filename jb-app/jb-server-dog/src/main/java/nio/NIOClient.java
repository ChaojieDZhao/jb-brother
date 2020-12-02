package nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIOClient 上午9:31:00
 * <p>
 * Copyright zhaocj Inc. All rights reserved.
 * Love ME Like Justin Bieber.
 */
public class NIOClient
{
	public static Object sendCommand(String host, int port, String cmd)
	{
		try
		{
			SocketChannel sc = SocketChannel.open();
			sc.connect(new InetSocketAddress(host, port));

			ByteBuffer buffer = ByteBuffer.allocate(cmd.getBytes().length);
			buffer.put(cmd.getBytes());
			buffer.flip();
			sc.write(buffer);
			sc.shutdownOutput();

			ByteBuffer readbuffer = ByteBuffer.allocate(1024);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = -1;
			while (true)
			{
				readbuffer.clear();
				len = sc.read(readbuffer);
				if (len == -1)
					break;
				readbuffer.flip();
				while (readbuffer.hasRemaining())
				{
					bos.write(readbuffer.get());
				}
			}
			sc.close();
			return new String(bos.toByteArray());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
