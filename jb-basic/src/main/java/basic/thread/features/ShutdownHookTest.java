package basic.thread.features;

/**
 * shutdownhook的使用场景：
 * 程序正常退出
 * 使用System.exit()
 * 终端使用Ctrl+C触发的中断
 * 系统关闭
 * OutOfMemory宕机
 * 使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）
 */
public class ShutdownHookTest
{
	public static void main(String[] args)
	{
		Object o = new Object()
		{
			@Override
			protected void finalize()
				throws Throwable
			{
				// 一旦垃圾收集器准备好释放对象占用的存储空间，它首先调用finalize()
				System.out.println("running finalize......");
			}
		};
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run()
			{
				System.out.println("running shutdown hook....");
			}
		});
		o = null;
		System.gc();
		System.out.println("Calling system exit");
		System.exit(0);
	}
}
