package algorithem.sortingalgorithm;

/**
 * Interface to be implemented by sorting algorithm implementations in this repository.
 */
public abstract class SortingAlgorithm
{

	public abstract void sort(int[] numbers);

	protected void swap(int[] numbers, int i, int j)
	{
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

	protected void validateInput(int[] numbers)
	{
		if (numbers == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance as parameter.");
		}
	}
}
