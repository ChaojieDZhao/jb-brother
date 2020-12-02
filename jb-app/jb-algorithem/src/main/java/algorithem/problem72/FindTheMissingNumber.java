package algorithem.problem72;

/**
 * An array A contains all the integers from 0 through N, except for fileDownload number which is missing.
 * Can you write a method to find that number?
 */
public class FindTheMissingNumber
{

	/**
	 * Iterative algorithm to solve this problem. This solution is based on the summation definition,
	 * if the array has every element from 0 to N except fileDownload, then we can calculate the sum of every
	 * element in the summation and the real sum to subtract both values and find the missing number.
	 * The complexity order in time terms is O(N) where N is the number o elements in the array. In
	 * space terms, the complexity order is O(1) because we are not using any auxiliary data
	 * structure with a size related to the input data to resolve this problem.
	 */
	public int find(int[] numbers)
	{
		validateInput(numbers);

		int length = numbers.length;
		int idealSum = length * (length + 1) / 2;
		int sum = calculateSum(numbers);

		int missingNumber = idealSum - sum;
		return missingNumber;
	}

	private int calculateSum(int[] numbers)
	{
		int sum = 0;
		for (int n : numbers)
		{
			sum += n;
		}
		return sum;
	}

	private void validateInput(int[] numbers)
	{
		if (numbers == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance of numbers.");
		}
		if (numbers.length == 0)
		{
			throw new IllegalArgumentException("You can't pass an empty array");
		}
	}
}
