package algorithem.problem6;
//Consider the past, and you shall know the future

/**
 * Given two already sorted arrays full of integers, write a method to merge it into other and keep
 * the sorting.
 */
public class MergeSortedArrays
{

	/**
	 * The complexity order of this algorithm in space terms is O(N+M) where N is the length of the
	 * first array and M is the length of the second array. In time terms the complexity order is
	 * equivalent, because we are going to iterate over array1 and array2 to merge every element.
	 */
	public int[] merge(int[] array1, int[] array2)
	{
		validateArrays(array1, array2);

		int[] result = new int[array1.length + array2.length];

		int i = 0, j = 0, k = 0;
		while (i < array1.length && j < array2.length)
		{
			if (array1[i] < array2[j])
			{
				result[k++] = array1[i++];
			}
			else
			{
				result[k++] = array2[j++];
			}
		}

		while (i < array1.length)
		{
			result[k++] = array1[i++];
		}
		while (j < array2.length)
		{
			result[k++] = array2[j++];
		}
		return result;
	}

	private void validateArrays(int[] array1, int[] array2)
	{
		if (array1 == null || array2 == null)
		{
			throw new IllegalArgumentException("You can't use null arrays as parameters.");
		}
	}
}
