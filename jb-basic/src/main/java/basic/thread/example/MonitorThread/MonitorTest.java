package basic.thread.example.MonitorThread;

public class MonitorTest
{
	public static void main(String[] args)
		throws InterruptedException
	{
		MonitorManager monitor = new MonitorManager();
		monitor.startMonitor();
		Thread.sleep(500000);
	}
}
