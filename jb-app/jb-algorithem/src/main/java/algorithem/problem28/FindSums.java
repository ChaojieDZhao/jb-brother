package algorithem.problem28;

import algorithem.pair.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array of numbers and a Integer as input, can you return a list of pairs in the input
 * array that can sum the input value.
 * <p>
 * For example: Input 7,{5,2,6,1,9} Output {5,2},{6,1}
 */
public class FindSums
{

	/**
	 * Iterative solution to this problem. Based on find the number that matches with a given number
	 * in the array, the complexity order of this algorithm is O(N^2) in time terms and O(N) in space
	 * terms.
	 */
	public List<Pair<Integer, Integer>> find(int[] numbers, int value)
	{
		validateInput(numbers);
		List<Pair<Integer, Integer>> sums = new LinkedList<Pair<Integer, Integer>>();
		for (int i = 0; i < numbers.length; i++)
		{
			for (int j = 0; j < numbers.length; j++)
			{
				if (numbers[i] + numbers[j] == value)
				{
					sums.add(new Pair(numbers[i], numbers[j]));
				}
			}
		}
		return sums;
	}

	/**
	 * Another iterative solution with a much better performance. This algorithm has a complexity
	 * order in space terms of O(N) where N is the number of elements in the array and O(N) in time
	 * terms because we are going to iterate over the numbers array just fileDownload time instead of two. The
	 * base idea in this algorithm is to store in a map the number you need to complete the sum just
	 * in cause you can find it in the future.
	 */
	public List<Pair<Integer, Integer>> findLinearComplexityOrder(int[] numbers, int n)
	{
		validateInput(numbers);
		List<Pair<Integer, Integer>> sums = new LinkedList<Pair<Integer, Integer>>();
		Map<Integer, Integer> partialElements = new HashMap<Integer, Integer>();
		for (int number : numbers)
		{
			if (partialElements.containsKey(number))
			{
				sums.add(new Pair<Integer, Integer>(number, partialElements.get(number)));
			}
			else
			{
				partialElements.put(n - number, number);
			}
		}
		return sums;
	}

	private void validateInput(int[] numbers)
	{
		if (numbers == null)
		{
			throw new IllegalArgumentException("You can't pass a null array of numbers.");
		}
	}
}
