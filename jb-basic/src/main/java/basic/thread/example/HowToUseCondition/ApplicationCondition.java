package basic.thread.example.HowToUseCondition;

/**
 * @describe
 */
public class ApplicationCondition
{

	public static void main(String[] args)
		throws InterruptedException
	{
		Service service = new Service();
		Runnable runnable01 = new ServiceThread01(service);
		Runnable runnable02 = new ServiceThread02(service);

		new Thread(runnable01, "THREAD-A").start();
		new Thread(runnable02, "THREAD-B").start();
		// 线程sleep2秒钟
		Thread.sleep(30000);
		// 唤醒所有持有conditionA的线程
		service.signallA();
		Thread.sleep(30000);
		// 唤醒所有持有conditionB的线程
		service.signallB();
	}

}
