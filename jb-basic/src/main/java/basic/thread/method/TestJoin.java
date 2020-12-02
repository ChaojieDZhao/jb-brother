package basic.thread.method;

import org.junit.Test;

/**
 * @describe 如果需要两个线程顺序执行，则可以使用join方法。
 */
public class TestJoin
{

	public static void main(String[] args)
		throws InterruptedException
	{
		System.out.println("main start");

		Thread t1 = new Thread(new Worker("WORKER-A"), "THREAD-A");
		Thread t2 = new Thread(new Worker("WORKER-B"), "THREAD-B");
		t1.start();
		t2.start();

		System.out.println("main end");
	}

	/**
	 * 这样的话 程序会顺序执行，t1执行完成后，t2才会执行
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void test1()
		throws InterruptedException
	{
		Thread t1 = new Thread(new Worker("WORKER-A"), "THREAD-A");
		Thread t2 = new Thread(new Worker("WORKER-B"), "THREAD-B");
		t1.start();
		t1.join();
		t2.start();
		t2.join();
	}

	/**
	 * 程序还没有执行，就已经退出了
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void test2()
		throws InterruptedException
	{
		Thread t1 = new Thread(new Worker("WORKER-A"), "THREAD-A");
		Thread t2 = new Thread(new Worker("WORKER-B"), "THREAD-B");
		t1.start();
		t2.start();
	}

	/**
	 * @throws InterruptedException
	 */
	@Test
	public void test3()
		throws InterruptedException
	{
		Thread t1 = new Thread(new Worker("WORKER-A"), "THREAD-A");
		Thread t2 = new Thread(new Worker("WORKER-B"), "THREAD-B");
		t1.start();
		t2.start();
		t1.join();  //这里t1开始阻塞，但是t2已经开始执行了。
		t2.join();
	}

	/**
	 * 相当于自己阻塞自己，THREAD-A，THREAD-B执行完毕了线程也不会terminated
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void test4()
		throws InterruptedException
	{
		Thread t1 = new Thread(new Worker("WORKER-A"), "THREAD-A");
		Thread t2 = new Thread(new Worker("WORKER-B"), "THREAD-B");
		t1.start();
		t2.start();
		Thread.currentThread().join();
	}
}

class Worker implements Runnable
{

	private String name;

	public Worker(String name)
	{
		this.name = name;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			try
			{
				Thread.sleep(1000L);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(name);
		}
	}

}
