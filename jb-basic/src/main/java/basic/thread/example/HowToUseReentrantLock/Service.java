package basic.thread.example.HowToUseReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Service
{

	ReentrantLock lock = new ReentrantLock();

	public void servicMethod()
	{
		lock.lock();
		try
		{
			System.out.println(Thread.currentThread().getName() + " 进入service");
			// 设置线程睡眠
			Thread.sleep(3000);
			for (int i = 1; i <= 3; i++)
			{
				System.out.println("打印了: " + i);
			}
			System.out.println(Thread.currentThread().getName() + " 退出service");
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		lock.unlock();
	}

}
