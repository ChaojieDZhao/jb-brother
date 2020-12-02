package algorithem.problem40;

/**
 * Given an array with elements from 1 to N where N is the size of the array operate with the
 * array to put every element in the position marked with the value of the element. You can't use
 * any other data structure and take into account the final result has to reflex the values of
 * the original fileDownload, not the already modified fileDownload.
 */
public class MoveElementsToPositions
{

	/**
	 * Iterative solution to solve this problem. This is a bubble sort algorithm and the complexity
	 * order of this algorithm is O(N^2). In space terms, the complexity order is O(1). I've used
	 * this algorithm to resolve this problem because in the problem statement says you can't use any
	 * additional data structure.
	 */
	public void move(int[] array)
	{
		validateInput(array);
		int size = array.length;
		boolean swap = true;
		while (swap)
		{
			swap = false;
			for (int i = 0; i < size - 1; i++)
			{
				if (array[i] > array[i + 1])
				{
					swap = true;
					swapElements(array, i, i + 1);
				}
			}
		}
	}

	private void validateInput(int[] array)
	{
		if (array == null)
		{
			throw new IllegalArgumentException("You can't pass a null array as input.");
		}
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++)
		{
			checkIfElementIsInsideTheValidInterval(array[i], arraySize);
		}
	}

	private void checkIfElementIsInsideTheValidInterval(int number, int length)
	{
		if (number < 0 || number >= length)
		{
			throw new IllegalArgumentException("Input array contains an invalid element: " + number);
		}
	}

	private void swapElements(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
