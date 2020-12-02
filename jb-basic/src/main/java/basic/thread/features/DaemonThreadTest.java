package basic.thread.features;

/**
 * 守护线程,如果当所有非守护线程结束之后虚拟机不会管守护线程的死活直接退出。
 */
public class DaemonThreadTest extends Thread
{
	private int i = 0;

	public static void main(String[] args)
	{
		DaemonThreadTest daemonThread = new DaemonThreadTest();
		daemonThread.setDaemon(true);
		daemonThread.start();
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("用户线程main结束了，守护线程daemonThread不再工作");
	}

	public void run()
	{
		while (true)
		{
			try
			{
				i++;
				System.out.println("守护线程正在工作-i=" + (i));
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
