package basic.thread.features;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @describe
 */
public class ManyThreadTest
{
	public static void main(String[] args)
	{
		Runnable runnable = new MyRunnable01();
		new Thread(runnable, "THREAD_A").start();
		new Thread(runnable, "THREAD_B").start();
		new Thread(runnable, "THREAD_C").start();
		new Thread(runnable, "THREAD_D").start();
	}

	@Test
	public void test1()
	{
		Runnable runnable = new MyRunnable01();
		new Thread(runnable, "THREAD_A").start();
		new Thread(runnable, "THREAD_B").start();
		new Thread(runnable, "THREAD_C").start();
		new Thread(runnable, "THREAD_D").start();
	}

	@Test
	public void test2()
	{
		Runnable runnable = new MyRunnable02();
		new Thread(runnable, "THREAD_A").start();
		new Thread(runnable, "THREAD_B").start();
		new Thread(runnable, "THREAD_C").start();
		new Thread(runnable, "THREAD_D").start();
	}
}

class MyRunnable01 implements Runnable
{
	int count = 0;

	@Override
	synchronized public void run()
	{
		autoIncrement();
		try
		{
			Thread.sleep(10000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 计算了 " + "count:" + count);
	}

	private void autoIncrement()
	{
		count++;
	}
}

class MyRunnable02 implements Runnable
{
	int count = 0;

	//使用重入锁ReentrantLock
	Lock lock = new ReentrantLock();

	@Override
	public void run()
	{
		lock.lock();
		count++;
		System.out.println(Thread.currentThread().getName() + " 计算了 " + "count:" + count);
		lock.unlock();
	}
}
