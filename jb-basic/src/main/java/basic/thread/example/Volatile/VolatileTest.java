package basic.thread.example.Volatile;

/**
 * 代码(while (isRunning))主线程虽然对 isRunning 的变量进行了修改且有刷新回主内存中.
 * （《深入理解java虚拟机》中关于主内存与工作内存的交互协议提到变量在工作内存中改变后必须将该变化同步回主内存），
 * 但volatileThead线程读的仍是自己工作内存的旧值导致出现多线程的可见性问题，
 * 解决办法就是给 isRunning 变量加上volatile关键字。
 */
public class VolatileTest extends Thread
{
	private volatile boolean isRunning = true;

	public static void main(String[] args)
		throws InterruptedException
	{
		VolatileTest volatileThread = new VolatileTest();
		volatileThread.start();
		Thread.sleep(1000);
		volatileThread.setRunning(false);   //通过主线程去修改isRunning值
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public void run()
	{
		System.out.println("进行了run...............");
		while (isRunning)
		{
		}
		System.out.println("isRunning 的值被修改为为false,线程将被停止了");
	}
}
