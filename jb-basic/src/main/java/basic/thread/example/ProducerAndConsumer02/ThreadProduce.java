package basic.thread.example.ProducerAndConsumer02;

public class ThreadProduce implements Runnable
{

	private Service service;

	public ThreadProduce(Service service)
	{
		this.service = service;
	}

	@Override
	public void run()
	{
		for (; ; )
		{
			service.produce();
		}
	}

}
