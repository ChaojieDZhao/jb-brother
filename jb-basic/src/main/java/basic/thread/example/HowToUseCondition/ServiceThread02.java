package basic.thread.example.HowToUseCondition;

/**
 * @describe
 */
public class ServiceThread02 implements Runnable
{

	private Service service;

	public ServiceThread02(Service service)
	{
		this.service = service;
	}

	@Override
	public void run()
	{
		service.awaitB();
	}

}
