package basic.thread.features;

/**
 * @describe
 */
public class FirstThreadTest
{

	public static void main(String[] args)
	{
		FirstThread thread = new FirstThread();
		thread.start();
	}

}

class FirstThread extends Thread
{
	@Override
	public void run()
	{
		super.run();
		System.out.println("Hello World!");
	}
}