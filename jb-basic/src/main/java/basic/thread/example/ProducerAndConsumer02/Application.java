package basic.thread.example.ProducerAndConsumer02;

public class Application
{

	public static void main(String[] args)
	{
		Service service = new Service();
		Runnable produce = new ThreadProduce(service);
		Runnable consume = new ThreadConsume(service);
		new Thread(produce, "生产者  ").start();
		new Thread(consume, "消费者  ").start();
	}

}
