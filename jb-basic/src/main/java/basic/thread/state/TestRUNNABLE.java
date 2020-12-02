package basic.thread.state;

import java.io.IOException;

public class TestRUNNABLE
{
	public static void main(String[] args)
		throws IOException
	{
		Thread runnableThread = new Thread("Thread-A")
		{
			public void run()
			{
				for (int i = 0; i < Integer.MAX_VALUE; i++)
				{
					System.out.println(i);
				}
			}
		};
		runnableThread.start();
	}
}
