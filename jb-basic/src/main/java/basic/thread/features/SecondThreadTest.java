package basic.thread.features;

/**
 * @describe
 */
public class SecondThreadTest extends Thread
{
	static String alldios = "alldios";

	public static void main(String[] args)
	{
		SecondThreadTest secondThreadTest = new SecondThreadTest();
		secondThreadTest.setAlldios("heheda");
		System.out.println(alldios);
	}

	public void setAlldios(String heheda)
	{
		alldios = alldios + "  " + heheda;
		start();
		run();
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 3; i++)
		{
			alldios = alldios + "  " + i;
		}
	}
}
