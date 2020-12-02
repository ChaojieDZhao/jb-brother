package basic.thread.pool;

import java.util.concurrent.*;

public class TestCallableAndFuture
{
	public static void main(String[] args)
		throws ExecutionException, InterruptedException
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(new Callable<String>()
		{
			@Override
			public String call()
				throws Exception
			{
				return "MOBIN";
			}
		});
		System.out.println("任务的执行结果：" + future.get());
	}
}
