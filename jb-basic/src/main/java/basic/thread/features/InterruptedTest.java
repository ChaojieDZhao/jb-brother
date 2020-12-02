package basic.thread.features;

public class InterruptedTest
{
	public static void main(String[] args)
	{
		Thread.currentThread().interrupt();
		System.out.println("当前线程是否停止：  " + Thread.interrupted());
		System.out.println("当前线程是否停止：  " + Thread.interrupted());
		System.out.println("当前线程是否停止：  " + Thread.interrupted());
	}
}
