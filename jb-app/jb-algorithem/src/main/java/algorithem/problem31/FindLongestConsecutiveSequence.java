package algorithem.problem31;

/**
 * Given an array full of integers, can you write a method to findIterative the length of the
 * longest
 * consecutive sequence inside the array?
 */
public class FindLongestConsecutiveSequence
{

	/**
	 * Iterative solution for this algorithm. This algorithm is based on a double pointer at the
	 * start and end of the sequence and the complexity order in time terms is O(N) where N is the
	 * number of elements in the array. In space terms, the complexity order is O(1) because we are
	 * not using any additional data structure to keep the result.
	 */
	public int findIterative(int[] numbers)
	{
		validateInput(numbers);
		if (numbers.length == 0)
		{
			return 0;
		}

		boolean consecutiveSequenceFound = false;
		int result = 0;
		int start = 0, end = 0;
		for (int i = 1; i < numbers.length; i++)
		{
			if (numbers[i - 1] < numbers[i])
			{
				end = i;
			}
			else
			{
				start = i;
			}
			if (end - start > result)
			{
				consecutiveSequenceFound = true;
				result = end - start;
			}
		}
		if (consecutiveSequenceFound)
		{
			return result + 1;
		}
		else
		{
			return result;
		}
	}

	/**
	 * Tail recursive solution for this algorithm. The complexity order of this algorithm is similar
	 * to the previous implementation, O(N) in time terms and O(1) in space terms.
	 */
	public int findRecursive(int[] array)
	{
		validateInput(array);
		return findRecursiveInner(array, 1, 0, 0);
	}

	private int findRecursiveInner(int[] array, int i, int acc, int maxLength)
	{
		if (array.length == 0)
		{
			return 0;
		}
		else if (array.length == i)
		{
			return maxLength == 0 ? 0 : 1 + maxLength;
		}
		else
		{
			int previous = array[i - 1];
			int current = array[i];
			if (previous >= current)
			{
				maxLength = acc > maxLength ? acc : maxLength;
				return findRecursiveInner(array, i + 1, 0, maxLength);
			}
			else
			{
				acc++;
				maxLength = acc > maxLength ? acc : maxLength;
				return findRecursiveInner(array, i + 1, acc, maxLength);
			}
		}
	}

	private void validateInput(int[] numbers)
	{
		if (numbers == null)
		{
			throw new IllegalArgumentException("You can't pass a null array as input.");
		}
	}
}
