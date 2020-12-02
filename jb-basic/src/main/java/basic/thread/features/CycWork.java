package basic.thread.features;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果要时间很多线程在同一事件的发生点同时进行，该怎样操作。
 * CyclicBarrier（栅栏）  等待多个await的线程数量到大一定值，则开始一同执行。
 * 或者使用countdownLantch  每次执行完毕后 countdown减一 ，等到数值等于0的时候，才会让等待的线程，同时继续触发执行。
 */
public class CycWork implements Runnable
{

	private CyclicBarrier cyclicBarrier;

	private String name;

	public CycWork(CyclicBarrier cyclicBarrier, String name)
	{
		this.name = name;
		this.cyclicBarrier = cyclicBarrier;
	}

	public static void main(String[] args)
	{
		ExecutorService executorpool = Executors.newFixedThreadPool(3);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

		CycWork work1 = new CycWork(cyclicBarrier, "张三");
		CycWork work2 = new CycWork(cyclicBarrier, "李四");
		CycWork work3 = new CycWork(cyclicBarrier, "王五");

		executorpool.execute(work1);
		executorpool.execute(work2);
		executorpool.execute(work3);

		executorpool.shutdown();

	}

	@Override
	public void run()
	{

		System.out.println(name + "正在打桩，毕竟不轻松。。。。。");

		try
		{
			Thread.sleep(new Random().nextInt(20000));
			System.out.println(name + "不容易，终于把桩打完了。。。。");
			cyclicBarrier.await();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(name + "：其他逗b把桩都打完了，又得忙活了。。。");

	}

}