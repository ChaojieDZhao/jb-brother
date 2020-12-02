package basic.thread.method;

public class TestSleep
{
	public static void main(String[] args)
		throws InterruptedException
	{
		Thread.currentThread().setName("THREAD_SLEEP");
		Thread.sleep(10000);
	}
}
