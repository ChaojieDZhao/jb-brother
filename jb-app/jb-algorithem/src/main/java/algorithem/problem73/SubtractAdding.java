package algorithem.problem73;

/**
 * Given two integers passed as argument, can you implement the subtract operator using just "+" as
 * arithmetic operator?
 */
public class SubtractAdding
{

	/**
	 * Iterative algorithm to resolve this problem. This solution is based on the subtraction
	 * definition where the subtraction is defined as the sum of the second number negated.
	 */
	public int subtract(int inputA, int inputB)
	{
		return inputA + negate(inputB);
	}

	private int negate(int number)
	{
		int increment = number > 0 ? -1 : 1;
		int result = 0;
		while (number != 0)
		{
			result += increment;
			number += increment;
		}
		return result;
	}
}
