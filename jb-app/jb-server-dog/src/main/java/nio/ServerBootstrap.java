package nio;

public class ServerBootstrap
{
	public static void main(String[] args)
	{
		NIOServer nioServer = new NIOServer(8888);
		nioServer.start();
	}
}
