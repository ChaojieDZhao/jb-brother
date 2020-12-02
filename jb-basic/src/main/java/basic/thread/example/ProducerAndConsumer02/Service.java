package basic.thread.example.ProducerAndConsumer02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Service
{

	private Lock lock = new ReentrantLock();

	/**
	 * true代表还有产品，false代表没有产品。
	 */
	private boolean flag = false;

	private Condition condition = lock.newCondition();

	private int number = 1;

	/**
	 * 生产者生产
	 */
	public void produce()
	{
		try
		{
			lock.lock();
			while (flag == true)
			{
				//如果还有产品   不生产了
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----生产-----");
			number++;
			System.out.println("number: " + number);
			flag = true;
			// 提醒消费者消费
			condition.signalAll();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}

	/**
	 * 消费者消费
	 */
	public void consume()
	{
		try
		{
			lock.lock();
			while (flag == false)
			{
				//如果没有产品了  无法消费 然后等待
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----消费-----");
			number--;
			System.out.println("number: " + number);
			flag = false;
			// 提醒生产者生产
			condition.signalAll();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}
}
