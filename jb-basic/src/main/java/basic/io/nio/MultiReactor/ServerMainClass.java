package basic.io.nio.MultiReactor;

import java.io.IOException;

public class ServerMainClass
{

	public static void main(String[] args)
		throws IOException
	{
		new Thread(new ServerReactor(9003)).start();
	}

}
