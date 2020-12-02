package basic.thread.example.HowToUseReentrantLock;

public class ServiceThread03 implements Runnable
{

	private Service myservice;

	public ServiceThread03(Service myservice)
	{
		this.myservice = myservice;
	}

	@Override
	public void run()
	{
		myservice.servicMethod();
	}

}
