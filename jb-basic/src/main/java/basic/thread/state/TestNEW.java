package basic.thread.state;

import java.io.IOException;

public class TestNEW
{
	public static void main(String[] args)
		throws IOException
	{
		Thread t = new Thread();
		System.out.println(t.getState());
	}
}
