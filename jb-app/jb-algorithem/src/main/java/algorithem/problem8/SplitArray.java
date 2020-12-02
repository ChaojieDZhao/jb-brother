package algorithem.problem8;
//Consider the past, and you shall know the future

/**
 * Given an array full of integers positive or negative, write a method to move every negative number
 * to the left and every positive number to the right. Take into account that the order of these
 * elements among the same group(positive or negative) into the array doesn't care.
 * <p>
 * Input: [1,2,3,-1,-2,-3] Output: [-2,-1,-3,2,3,1]
 */
public class SplitArray
{

	/**
	 * The first solution implemented for this problem is based on the "Bubble Sort Algorithm".
	 * <p>
	 * The time complexity of this solution is O(N^2) where N is number of elements in the array. It is even worse
	 * than the bubble sort algorithm because to check if we have to swap between any pair of elements is really
	 * expensive. The space complexity of this algorithm is O(1) because we are not
	 * using any additional data structure.
	 */
	public void splitSorting(int[] array)
	{
		if (array == null)
		{
			throw new IllegalArgumentException("Array passed as parameter can't be null.");
		}

		boolean flag = true;
		while (flag)
		{
			flag = false;
			for (int j = 0; j < array.length - 1; j++)
			{
				if (array[j] > array[j + 1])
				{
					swap(array, j, j + 1);
					flag = true;
				}
			}
		}
	}

	/**
	 * The second solution for this problem is much faster than the previous fileDownload. Here, instead of using a sorting
	 * algorithm we are going over the array from left to right using two pointers and swapping
	 * elements if needed. The time complexity of this algorithm is O(N) where N is the
	 * number of elements in the array. The space complexity is also O(1) because we are not using any additional
	 * data structure here as well.
	 */
	public void splitSwappingIterative(int[] array)
	{
		if (array == null)
		{
			throw new IllegalArgumentException("Array passed as parameter can't be null.");
		}

		int left = 0;
		int right = array.length - 1;
		while (left < right)
		{
			boolean shouldChangeLeft = array[left] >= 0;
			boolean shouldChangeRight = array[right] < 0;
			if (shouldChangeLeft && shouldChangeRight)
			{
				swap(array, left, right);
				left++;
				right--;
			}
			else
			{
				if (!shouldChangeLeft)
				{
					left++;
				}
				else if (!shouldChangeRight)
				{
					right--;
				}
			}
		}
	}

	/**
	 * Using partition Method of Quicksort to split the array.
	 * Its called Hoare's Partitioning Algorithm
	 */
	public void splitSwappingPartition(int[] array)
	{
		if (array == null)
		{
			throw new IllegalArgumentException("Array passed as parameter can't be null.");
		}

		int left = 0;
		int right = array.length - 1;
		while (left < right)
		{
			while (array[left] < 0 && left < right)
			{
				left++;
			}
			while (array[right] >= 0 && left < right)
			{
				right--;
			}
			if (left < right)
			{
				swap(array, left, right);
				left++;
				right--;
			}
		}
	}

	/**
	 * This is a tail recursive solution for this problem. This implementation has the same time complexity,
	 * O(N). Here, the only change is how we are going to iterate over the array; in the previous
	 * implementation we used a classic iterative approach whereas in this solution we are using
	 * recursion to iterate. In this case, the space complexity is also O(1) because we are not using any additional data
	 * structure.
	 */
	public void splitSwappingRecursive(int[] array)
	{
		if (array == null)
		{
			throw new IllegalArgumentException("Array passed as parameter can't be null.");
		}

		if (array.length == 0)
		{
			return;
		}

		splitSwappingRecursiveInner(array, 0, array.length - 1);
	}

	private void splitSwappingRecursiveInner(int[] array, int left, int right)
	{
		if (left < right)
		{
			boolean shouldChangeLeft = array[left] >= 0;
			boolean shouldChangeRight = array[right] < 0;
			if (shouldChangeLeft && shouldChangeRight)
			{
				swap(array, left, right);
				splitSwappingRecursiveInner(array, left + 1, right - 1);
			}
			else
			{
				if (!shouldChangeLeft)
				{
					splitSwappingRecursiveInner(array, left + 1, right);
				}
				else if (!shouldChangeRight)
				{
					splitSwappingRecursiveInner(array, left, right - 1);
				}
			}
		}
	}

	private void swap(int[] array, int left, int right)
	{
		int aux = array[right];
		array[right] = array[left];
		array[left] = aux;
	}
}
