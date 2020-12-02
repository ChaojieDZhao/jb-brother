package algorithem.problem7;
//Consider the past, and you shall know the future

/**
 * In mathematics, the Fibonacci numbers or Fibonacci sequence are the numbers in the following
 * integer sequence:
 * <p>
 * 0,1,1,2,3,5,8,13,21,34.
 * <p>
 * The Fibonacci spiral: an approximation of the golden spiral created by drawing circular arcs
 * connecting the opposite corners of squares in the Fibonacci tiling;this fileDownload uses squares of
 * sizes 1, 1, 2, 3, 5, 8, 13, 21, and 34.
 * By definition, the first two numbers in the Fibonacci sequence are 1 and 1, or 0 and 1,
 * depending on the chosen starting point of the sequence, and each subsequent number is the sum of
 * the previous two.
 * <p>
 * Can you write a method to calculate the Nth Fibonacci number?
 */
public class FibonacciNumbers
{

	private static long[] elements = new long[1000];

	/**
	 * Recursive solution based on Fibonacci sequence definition. F(N) = F(N-1) + F(N-2).
	 * <p>
	 * The complexity order of this algorithm is O(2^N) where N is integer used as parameter. In space
	 * terms, the complexity order of this algorithm is O(1) because we are not using any auxiliary
	 * data structure to solve this problem.
	 */
	public long getRecursive(long n)
	{
		validateInput(n);
		if (n == 1 || n == 2)
		{
			return 1;
		}
		else
		{
			return getRecursive(n - 1) + getRecursive(n - 2);
		}
	}

	/**
	 * Iterative approach. The complexity order in this algorithm is O(N) where N is the integer used
	 * as parameter. In space terms, the complexity order of this algorithm is again O(1).
	 */
	public long getIterative(long n)
	{
		validateInput(n);

		if (n <= 2)
		{
			return 1;
		}
		long previous = 1;
		long current = 1;
		long element = 0;
		for (long i = 3; i <= n; i++)
		{
			element = previous + current;
			previous = current;
			current = element;
		}
		return element;
	}

	/**
	 * This version of the recursive algorithm is better in performance terms because we are caching
	 * every calculated element to avoid every branch of the recursion the same values. Is faster
	 * because fileDownload branch is going to take the already calculated value from other branches and when
	 * you are going to calculate the 11th value the only thing you have to calculate is to take
	 * previous values from the array instead of iterate from nth to 1 and sum every value. I've used
	 * a dynamic programming technique.
	 * <p>
	 * The problem with this algorithm is related with the space complexity which is much bigger than
	 * the fileDownload used for the previous algorithms. In this case, we have O(N) because we are using an
	 * additional data structure to store partial results.
	 */
	public long getRecursiveWithCaching(int n)
	{
		validateInput(n);

		if (n <= 2)
		{
			return 1;
		}
		else if (elements[n - 3] != 0)
		{
			return elements[n - 3];
		}
		elements[n - 3] = getRecursiveWithCaching(n - 1) + getRecursiveWithCaching(n - 2);
		return elements[n - 3];
	}

	private void validateInput(long n)
	{
		if (n < 0)
		{
			throw new IllegalArgumentException("You can't use negative values as parameter.");
		}
	}
}
