package algorithem.problem5;
//Consider the past, and you shall know the future

/**
 * Given two integers passed as parameter, can you write a method to divide the division of that
 * parameters using subtraction operator?
 */
public class DivideUsingSubtraction
{

	/**
	 * This algorithm is based on the mathematical property related with the division where a
	 * division es a continuous subtraction.
	 * <p>
	 * In space terms, the complexity of this algorithm is O(1) because we are not using any
	 * additional data structure. In time terms, the complexity of this algorithm is O(N) where N is
	 * the number of times you can subtract the divider to the dividend.
	 */
	public float divideIterative(int dividend, int divider)
	{
		validateInput(divider);
		float result = 0;
		while (dividend > divider)
		{
			dividend -= divider;
			result++;
		}
		float decimalPart = (float)dividend / (float)divider;
		result += decimalPart;
		return result;
	}

	/**
	 * Recursive version of the previous algorithm .The complexity order in space and memory terms is
	 * the same than the iterative version. However this algorithm is more expensive in computation
	 * terms because recursion is going to add a new frame to the stack every time a recursive call
	 * is performed. Review this algorithm and take into account this algorithm is not tail
	 * recursive.
	 */
	public float divideRecursive(int dividend, int divider)
	{
		validateInput(divider);
		if (dividend == 0)
		{
			return 0f;
		}
		else if (dividend < divider)
		{
			return (float)dividend / (float)divider;
		}
		else
		{
			return 1f + divideRecursive(dividend - divider, divider);
		}
	}

	private void validateInput(int divider)
	{
		if (divider == 0)
		{
			throw new IllegalArgumentException(
				"You can't divide using 0 as divider. That operation is not defined");
		}
	}
}
