package basic.thread.example.ProducerAndConsumer02;

public class ThreadConsume implements Runnable
{

	private Service service;

	public ThreadConsume(Service service)
	{
		super();
		this.service = service;
	}

	@Override
	public void run()
	{
		for (; ; )
		{
			service.consume();
		}
	}

}
