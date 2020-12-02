package basic.thread.features;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试定时器，在某一个时间点执行
 */
public class TimerDaemonTest
{
	private static Timer timer = new Timer(true); //定时器为守护线程

	public static void main(String[] args)
	{
		MyTask myTask = new MyTask();        //创建定时任务

		try
		{
			DateTime dateTime = new DateTime().plusSeconds(10);
			System.out.println("任务计划启动时间：" + dateTime.toString(DateTimeFormat.mediumDateTime()) + "  当前时间：" +
				new DateTime().toString(DateTimeFormat.mediumDateTime()));
			timer.schedule(myTask, dateTime.toDate());
			try
			{
				Thread.sleep(600000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static public class MyTask extends TimerTask
	{
		public void run()
		{
			System.out.println("运行时间为：" + new DateTime().toString(DateTimeFormat.mediumDateTime()));
		}
	}
}
