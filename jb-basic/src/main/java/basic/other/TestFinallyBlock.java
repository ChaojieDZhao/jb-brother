package basic.other;

/**
 * @describe The finally Block
 * The finally block always executes when the try block exits. This ensures that the finally block is executed even if an unexpected exception occurs.
 * But finally is useful for more than just exception handling — it allows the programmer to avoid having cleanup code accidentally bypassed by a return,continue, or break.
 * Putting cleanup code in a finally block is always a good practice, even when no exceptions are anticipated.
 * Note: If the JVM exits while the try or catch code is being executed, then the finally block may not execute. Likewise, if the thread executing the try or catch code is interrupted or killed,
 * the finally block may not execute even though the application as a whole continues.
 */
public class TestFinallyBlock
{
	public static void main(String[] args)
	{
		System.out.println("return value of test(): " + test());
	}

	public static int test()
	{
		int i = 1;
		System.out.println("the previous statement of try block");
		i = i / 0;
		try
		{
			System.out.println("try block");
			return i;
		}
		finally
		{
			System.out.println("finally block");
		}
	}

	public static int test2()
	{
		int i = 1;
		try
		{
			System.out.println("try block");
			System.exit(0);
			return i;
		}
		finally
		{
			System.out.println("finally block");
		}
	}
}