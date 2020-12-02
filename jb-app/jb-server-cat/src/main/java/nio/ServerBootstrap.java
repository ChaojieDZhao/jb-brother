package nio;

public class ServerBootstrap
{
	public static void main(String[] args)
	{
		NIOServer nioServer = new NIOServer(9999);
		nioServer.start();
	}
}
