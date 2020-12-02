package algorithem.problem80;

import algorithem.sortingalgorithm.SortingAlgorithm;

/**
 * Given an array full of integers implement a quick sort algorithm to sort the content inside
 * the array.
 */
public class QuickSort extends SortingAlgorithm
{

	/**
	 * Quicksort (sometimes called partition-exchange sort) is an efficient sorting algorithm, serving
	 * as a systematic method for placing the elements of an array in order. Developed by Tony Hoare
	 * in 1959 and published in 1961, it is still a commonly used algorithm for sorting. When
	 * implemented well, it can be about two or three times faster than its main competitors, merge
	 * sort and heapsort.
	 * <p>
	 * Quicksort is a comparison sort, meaning that it can sort items of any type for which a
	 * "less-than" relation (formally, a total order) is defined. In efficient implementations it is
	 * not a stable sort, meaning that the relative order of equal sort items is not preserved.
	 * Quicksort can operate in-place on an array, requiring small additional amounts of memory to
	 * perform the sorting.
	 * <p>
	 * Mathematical analysis of quicksort shows that, on average, the algorithm takes O(n log n)
	 * comparisons to sort n items. In the worst case, it makes O(n2) comparisons, though this
	 * behavior is rare.
	 */
	@Override
	public void sort(int[] numbers)
	{
		validateInput(numbers);
		quickSort(numbers, 0, numbers.length - 1);
	}

	private void quickSort(int[] numbers, int left, int right)
	{
		if (left < right)
		{
			int pivotIndex = partition(numbers, left, right);
			quickSort(numbers, left, pivotIndex - 1);  //sort left of pivot
			quickSort(numbers, pivotIndex, right);  //sort right of pivot
		}
	}

	private int partition(int[] numbers, int left, int right)
	{
		int pivot = numbers[right];
		while (left < right)
		{
			while (numbers[left] < pivot)
			{
				left++;
			}
			while (numbers[right] > pivot)
			{
				right--;
			}
			if (left <= right)
			{
				int temp = numbers[left];
				numbers[left] = numbers[right];
				numbers[right] = temp;
			}
		}
		return left; //pivot index
	}
}