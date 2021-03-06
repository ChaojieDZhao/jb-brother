package basic.thread.example.Volatile;

/**
 * volatile原子性
 * 步骤1：从内存中取出value
 * 步骤2:  计算value值
 * 步骤3:  重新将值刷新回内存
 * <p>
 * 当线程1在步骤2对value进行计算时，刚好其他线程也对value进行了修改，这时线程1返回的值就不是我们期望的值了，
 * 于是出现线程安全问题，所以volatile不能保证复合操作具有原子性；解决办法就是给increment方法加锁(lock/synchronized)或将变量原子类类型。
 */
public class VolatileTest01
{
	private volatile int value;  //将value变量声明成volatile类型

	public static void main(String[] args)
	{
		final VolatileTest01 volatileTest1 = new VolatileTest01();
		for (int i = 0; i < 100; i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					volatileTest1.increment();
				}
			}).start();
		}
	}

	public synchronized void increment()
	{
		value++;
		System.out.println(value);
	}
}
