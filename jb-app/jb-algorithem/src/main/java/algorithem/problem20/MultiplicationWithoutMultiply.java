package algorithem.problem20;

/**
 * Given two integers passed as parameter, can you write a method to multiply it and return the
 * result without use "*" operator?
 */
public class MultiplicationWithoutMultiply
{

	/**
	 * Iterative solution to this problem. This algorithm is based on the multiplication definition
	 * as
	 * a consecutive sum. The complexity order of this algorithm in time terms is O(N) where N is
	 * equals to n1 parameter. In space terms the complexity order of this algorithm is O(1) because
	 * we are not using any additional data structure related to the input size.
	 */
	public int calculate(int n1, int n2)
	{
		int result = 0;
		boolean negative = (n1 < 0 && n2 >= 0) || (n2 < 0 && n1 >= 0);
		boolean positive = !negative;
		n1 = Math.abs(n1);
		for (int i = 0; i < n1; i++)
		{
			if (negative && n2 > 0 || positive && n2 < 0)
				result -= n2;
			else
				result += n2;
		}

		return result;
	}
}
