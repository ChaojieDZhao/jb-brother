package basic.thread.lock;

public class TestSynchronized
{

	public static void init()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					while (true)
					{
						Thread.sleep(1000);
						Outprint.out("hadoop");
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		).start();

		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					while (true)
					{
						Thread.sleep(1000);
						Outprint.out("spark");
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		).start();
	}

	public static void main(String[] args)
	{
		TestSynchronized.init();
	}

	static class Outprint
	{
		public static void out(String str)
		{
			synchronized (Outprint.class)
			{
				for (int i = 0; i < str.length(); i++)
				{
					System.out.print(str.charAt(i));
				}
				System.out.println();
			}
		}
	}
}
