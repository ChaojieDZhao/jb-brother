package basic.io.nio.MultiReactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SocketWriteHandler extends SocketHandler
{
	private static int Index = 1;

	private int BLOCK = 4096;

	private ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);

	public SocketWriteHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector)
		throws IOException
	{
		super(dispatcher, sc, selector);
	}

	@Override
	public void runnerExecute(int readyKeyOps)
		throws IOException
	{
		if (readyKeyOps == SelectionKey.OP_WRITE)
		{
			String data = String.format("%d", Index);
			byte[] req = data.getBytes();
			sendbuffer.clear();

			sendbuffer = ByteBuffer.allocate(req.length);
			sendbuffer.put(req);
			sendbuffer.flip();
			socketChannel.write(sendbuffer);
			Index++;
			socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
		}
	}
}
