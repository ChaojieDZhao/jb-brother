package basic.thread.example.HowToUseReentrantLock;

import org.junit.Test;

import java.io.IOException;

/**
 * @describe
 */
public class TestReentrantLock
{
	/**
	 * 如果使用主线程执行则不会退出，使用TEST注解测试多线程必须要阻塞。
	 *
	 * @param args
	 */
	public static void main(String[] args)
		throws IOException
	{
		Service myservice1 = new Service();
		Service myservice2 = new Service();
		Service myservice3 = new Service();
		Runnable runnable1 = new ServiceThread01(myservice1);
		Runnable runnable2 = new ServiceThread02(myservice2);
		Runnable runnable3 = new ServiceThread03(myservice3);
		new Thread(runnable1, "THREAD_A").start();
		new Thread(runnable2, "THREAD_B").start();
		new Thread(runnable3, "THREAD_C").start();
	}

	@Test
	public void test1()
		throws IOException
	{
		Service myservice1 = new Service();
		Service myservice2 = new Service();
		Service myservice3 = new Service();
		Runnable runnable1 = new ServiceThread01(myservice1);
		Runnable runnable2 = new ServiceThread02(myservice2);
		Runnable runnable3 = new ServiceThread03(myservice3);
		new Thread(runnable1, "THREAD_A").start();
		new Thread(runnable2, "THREAD_B").start();
		new Thread(runnable3, "THREAD_C").start();
		//如果不阻塞，THREAD_A,B,C还没有运行就退出了，则什么都不会打印。
		System.in.read();
	}

	@Test
	public void test2()
		throws IOException
	{
		Service myservice = new Service();
		Runnable runnable1 = new ServiceThread01(myservice);
		Runnable runnable2 = new ServiceThread02(myservice);
		Runnable runnable3 = new ServiceThread03(myservice);
		new Thread(runnable1, "THREAD_A").start();
		new Thread(runnable2, "THREAD_B").start();
		new Thread(runnable3, "THREAD_C").start();
		//如果不阻塞，THREAD_A,B,C还没有运行就退出了，则什么都不会打印。
		System.in.read();
	}

}
