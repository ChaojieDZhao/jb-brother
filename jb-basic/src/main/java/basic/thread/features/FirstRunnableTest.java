package basic.thread.features;

import basic.proxy2kind.cglib.RealSubject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @describe
 */
public class FirstRunnableTest
{
	private final static Logger LOGGER = LoggerFactory.getLogger(RealSubject.class);

	private static long start;

	@Before
	public static void before()
	{
		start = System.nanoTime();
	}

	@Test
	public void test1()
		throws Exception
	{
		Thread.currentThread().setName("I_AM_MAIN_THREAD");
		FirstRunnable firstRunnable = new FirstRunnable();
		//如果直接调用run()方法，则不会新增线程，直接使用当前线程去执行。
		firstRunnable.run();
		LOGGER.info("" + (System.nanoTime() - start));
	}

	@Test
	public void test2()
	{
		Thread.currentThread().setName("I_AM_MAIN_THREAD");
		FirstRunnable firstRunnable = new FirstRunnable();
		//该方法调用后直接返回，因为使用了一个新线程去调用方法。
		new Thread(firstRunnable, "I_AM_NEW_THREAD").start();
		LOGGER.info("" + (System.nanoTime() - start));
	}
}

class FirstRunnable implements Runnable
{
	@Override
	public void run()
	{
		System.out.println(Thread.currentThread().getName());
		System.out.println("Hello World!");
		try
		{
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}