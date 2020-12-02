package basic.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆栈溢出
 */
public class StackOverflow
{
	int num = 1;

	public static void main(String[] agrs)
	{
		StackOverflow t = new StackOverflow();
		//t.testHeap();
		t.testStack();
	}

	public void testHeap()
	{//堆溢出
		List<byte[]> list = new ArrayList<byte[]>();
		int i = 0;
		while (true)
		{
			list.add(new byte[5 * 1024 * 1024]);
			System.out.println("count is: " + (++i));
		}
	}

	public void testStack()
	{ //栈溢出
		num++;
		this.testStack();
	}
}