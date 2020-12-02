package basic.thread.example.HowToUseReentrantLock;

public class ServiceThread02 implements Runnable
{

	private Service myservice;

	public ServiceThread02(Service myservice)
	{
		this.myservice = myservice;
	}

	@Override
	public void run()
	{
		myservice.servicMethod();
	}

}
