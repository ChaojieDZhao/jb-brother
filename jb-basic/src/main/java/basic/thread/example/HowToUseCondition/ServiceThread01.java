package basic.thread.example.HowToUseCondition;

/**
 * @describe
 */
public class ServiceThread01 implements Runnable
{

	private Service service;

	public ServiceThread01(Service service)
	{
		this.service = service;
	}

	@Override
	public void run()
	{
		service.awaitA();
	}

}
