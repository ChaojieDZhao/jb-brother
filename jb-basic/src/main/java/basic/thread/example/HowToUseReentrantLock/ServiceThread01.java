package basic.thread.example.HowToUseReentrantLock;

public class ServiceThread01 implements Runnable
{

	private Service myservice;

	public ServiceThread01(Service myservice)
	{
		this.myservice = myservice;
	}

	@Override
	public void run()
	{
		myservice.servicMethod();
	}

}
