package basic.io.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class WriteHandler implements CompletionHandler<Integer, ByteBuffer>
{
	private AsynchronousSocketChannel channel;

	private CountDownLatch latch;

	public WriteHandler(AsynchronousSocketChannel channel, CountDownLatch latch)
	{
		this.channel = channel;
		this.latch = latch;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer)
	{
		//如果消息没发送完，就继续发送直到完成
		if (buffer.hasRemaining())
		{
			channel.write(buffer, buffer, new WriteHandler(channel, latch));
		}
		else
		{
			//创建新的Buffer
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			//异步读，第三个参数为接收消息回调的业务Handler
			channel.read(readBuffer, readBuffer, new ReadHandler(channel));
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer buffer)
	{
		try
		{
			channel.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
