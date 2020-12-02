package basic.exception;

import java.io.FileNotFoundException;

public class CheckedExceptionOOM
{

	public static void main(String[] args)
	{
		try
		{
			test1();
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
		finally
		{
		}
	}

	/**
	 * UncheckedException 无需捕获
	 */
	public static void test1()
	{
		throw new NullPointerException();
	}

	/**
	 * CheckedException 需要捕获或者抛出
	 */
	public static void test2()
		throws FileNotFoundException
	{
		throw new FileNotFoundException();
	}
}

