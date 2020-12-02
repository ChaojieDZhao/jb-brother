package algorithem.problem45;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array full of integers, can you write a method to find the element repeated kTH times.
 */
public class FindNthMostRepeatedElement
{

	/**
	 * Iterative solution to solve this problem. This algorithm is based on a complexity order equals
	 * to O(N) and space O(N) because you need to go through the full set numbers two times and you
	 * need a map to store the number of repetitions for every element.
	 */
	public int find(int[] numbers, int position)
	{
		validateInput(numbers, position);
		Integer result = null;
		Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
		for (int i : numbers)
		{
			if (counter.get(i) == null)
			{
				counter.put(i, 1);
			}
			else
			{
				counter.put(i, counter.get(i) + 1);
			}
		}
		for (Integer candidate : counter.keySet())
		{
			if (counter.get(candidate) == position)
			{
				result = candidate;
				break;
			}
		}
		validateResult(result);
		return result;
	}

	private void validateInput(int[] numbers, int position)
	{
		if (numbers == null || position <= 0)
		{
			throw new IllegalArgumentException(
				"You can't pass null arrays or position values minor than 0 as parameter.");
		}
	}

	private void validateResult(Integer result)
	{
		if (result == null)
		{
			throw new IllegalArgumentException(
				"There are no elements repeated n times in the array passed as argument");
		}
	}
}
