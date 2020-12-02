package algorithem.problem39;

/**
 * Given an array of elements, write a method to multiply every value for the rest of elements and
 * return a new array with this values. Can you resolve this problem without use "/" operator?
 */
public class MultiplyArrayElements
{

	/**
	 * Iterative solution to solve this problem. The complexity order of this algorithm is O(N) time
	 * and space terms where N is the number of elements in the input array.
	 */
	public int[] multiply(int[] input)
	{
		if (input == null)
		{
			throw new IllegalArgumentException("You can't pass a null array as input.");
		}
		if (input.length == 0)
		{
			return input;
		}

		int[] front = new int[input.length];
		int[] rear = new int[input.length];
		int[] output = new int[input.length];

		front[0] = 1;
		rear[input.length - 1] = 1;
		for (int i = 1; i < input.length; i++)
		{
			front[i] = front[i - 1] * input[i - 1];
		}
		for (int i = input.length - 2; i >= 0; i--)
		{
			rear[i] = rear[i + 1] * input[i + 1];
		}
		for (int i = 0; i < input.length; i++)
		{
			output[i] = front[i] * rear[i];
		}
		return output;
	}
}
