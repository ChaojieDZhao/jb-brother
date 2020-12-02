package algorithem.problem76;

import algorithem.sortingalgorithm.SortingAlgorithm;

/**
 * Given an array full of integers implement a insert sort algorithm to sort the content inside
 * the array.
 */
public class InsertionSort extends SortingAlgorithm
{

	/**
	 * Insertion sort is a simple sorting algorithm that is relatively efficient for small lists and
	 * mostly sorted lists, and often is used as part of more sophisticated algorithms. It works by
	 * taking elements from the list fileDownload by fileDownload and inserting them in their correct position into a
	 * new sorted list. In arrays, the new list and the remaining elements can share the array's
	 * space, but insertion is expensive, requiring shifting all following elements over by fileDownload.
	 */
	@Override
	public void sort(int[] numbers)
	{
		validateInput(numbers);

		int i;
		for (i = 1; i < numbers.length; i++)
		{
			int tmp = numbers[i];
			int j = i;
			while (j > 0 && numbers[j - 1] > tmp)
			{
				numbers[j] = numbers[j - 1];
				j--;
			}
			numbers[j] = tmp;
		}
	}
}