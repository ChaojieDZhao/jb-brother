package algorithem.problem79;

import algorithem.sortingalgorithm.SortingAlgorithm;

/**
 * Given an array full of integers implement a merge sort algorithm to sort the content inside
 * the array.
 */
public class MergeSort extends SortingAlgorithm
{

	private int[] numbers;

	private int[] helper;

	private int number;

	/**
	 * Merge sort takes advantage of the ease of merging already sorted lists into a new sorted list.
	 * It starts by comparing every two elements (i.e., 1 with 2, then 3 with 4...) and swapping them
	 * if the first should come after the second. It then merges each of the resulting lists of two
	 * into lists of four, then merges those lists of four, and so on; until at last two lists are
	 * merged into the final sorted list. Of the algorithms described here, this is the first that
	 * scales well to very large lists, because its worst-case running time is O(n log n). It is also
	 * easily applied to lists, not only arrays, as it only requires sequential access, not random
	 * access. However, it has additional O(n) space complexity, and involves a large number of
	 * copies in simple implementations.
	 */
	@Override
	public void sort(int[] numbers)
	{
		validateInput(numbers);

		this.numbers = numbers;
		number = numbers.length;
		this.helper = new int[number];
		mergeSort(0, number - 1);
	}

	private void mergeSort(int low, int high)
	{
		if (low < high)
		{
			// Get the middle element index
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			mergeSort(low, middle);
			// Sort the right side of the array
			mergeSort(middle + 1, high);
			// Combine partinal sorting
			merge(low, middle, high);
		}
	}

	private void merge(int low, int middle, int high)
	{
		// Copy both parts into the helper array
		for (int i = low; i <= high; i++)
		{
			helper[i] = numbers[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back to the original array
		while (i <= middle && j <= high)
		{
			if (helper[i] <= helper[j])
			{
				numbers[k] = helper[i];
				i++;
			}
			else
			{
				numbers[k] = helper[j];
				j++;
			}
			k++;
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle)
		{
			numbers[k] = helper[i];
			k++;
			i++;
		}
		//Add
		// Copy the rest of the right side of the array into the target array
		while (j <= high)
		{
			numbers[k] = helper[j];
			k++;
			j++;
		}
	}
}
