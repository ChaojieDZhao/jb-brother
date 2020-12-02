package basic.thread.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class TestFileSearchExecutor
{
	public static void main(String[] args)
		throws ExecutionException, InterruptedException
	{
		String path = "G:\\test";
		String keyword = "heheda";
		int c = 0;
		File[] files = new File(path).listFiles();
		ExecutorService pool = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> rs = new ArrayList<>();
		for (File file : files)
		{
			MatchCount count = new MatchCount();
			count.file = file;
			count.keyword = keyword;
			Future<Integer> task = pool.submit(count);
			rs.add(task); //将任务的返回结果添加到集合中
		}

		for (Future<Integer> f : rs)
		{
			c += f.get();
		}
		System.out.println("当前的最大线程数：" + ((ThreadPoolExecutor)pool).getLargestPoolSize());
		System.out.println("包含关键字的总文件数为：" + c);
	}
}

class MatchCount implements Callable<Integer>
{

	public File file;

	public String keyword;

	public ExecutorService pool;

	private Integer count = 0;

	public Integer call()
		throws Exception
	{
		if (search(file))
			count++;
		return count;
	}

	public boolean search(File file)
	{
		boolean founded = false;
		try (Scanner scanner = new Scanner(new FileInputStream(file)))
		{
			while (!founded && scanner.hasNextLine())
			{
				if (scanner.nextLine().contains(keyword))
					founded = true;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return founded;
	}
}