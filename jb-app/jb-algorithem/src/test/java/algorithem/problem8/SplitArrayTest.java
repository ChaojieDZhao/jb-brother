package algorithem.problem8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SplitArrayTest
{

	SplitArray splitArray;

	@Before
	public void setUp()
	{
		splitArray = new SplitArray();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysSorting()
	{
		splitArray.splitSorting(null);
	}

	@Test
	public void shouldSupportEmptyArraysSorting()
	{
		int[] array = new int[0];

		splitArray.splitSorting(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfPositiveNumbersSorting()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSorting(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfNegativeNumbersSorting()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSorting(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftSorting()
	{
		int[] array = {1, 2, -1, -3, 4, -6};

		splitArray.splitSorting(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithWithMoreNegativeElementsSorting()
	{
		int[] array = {1, -2, -1, -3, 4, -6};

		splitArray.splitSorting(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysWithTwoPointersIterative()
	{
		splitArray.splitSwappingIterative(null);
	}

	@Test
	public void shouldSupportEmptyArraysWithTwoPointersIterative()
	{
		int[] array = new int[0];

		splitArray.splitSwappingIterative(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfPositiveNumbersWithTwoPointersIterative()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSwappingIterative(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfNegativeNumbersWithTwoPointersIterative()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSwappingIterative(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithTwoPointersIterative()
	{
		int[] array = {1, 2, -1, -3, 4, -6};

		splitArray.splitSwappingIterative(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithWithMoreNegativeElementsTwoPointersIterative()
	{
		int[] array = {1, -2, -1, -3, 4, -6};

		splitArray.splitSwappingIterative(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysWithPartition()
	{
		splitArray.splitSwappingPartition(null);
	}

	@Test
	public void shouldSupportEmptyArraysWithPartition()
	{
		int[] array = new int[0];

		splitArray.splitSwappingPartition(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfPositiveNumbersWithPartition()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSwappingPartition(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfNegativeNumbersWithPartition()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSwappingPartition(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithPartition()
	{
		int[] array = {1, 2, -1, -3, 4, -6};

		splitArray.splitSwappingPartition(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithWithMoreNegativeElementsWithPartition()
	{
		int[] array = {1, -2, -1, -3, 4, -6};

		splitArray.splitSwappingPartition(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysWithTwoPointersRecursive()
	{
		splitArray.splitSwappingRecursive(null);
	}

	@Test
	public void shouldSupportEmptyArraysWithTwoPointersRecursive()
	{
		int[] array = new int[0];

		splitArray.splitSwappingRecursive(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfPositiveNumbersWithTwoPointersRecursive()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSwappingRecursive(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldWorkWithAnArrayFullOfNegativeNumbersWithTwoPointersRecursive()
	{
		int[] array = {1, 2, 1, 3, 4, 6};

		splitArray.splitSwappingRecursive(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithTwoPointersRecursive()
	{
		int[] array = {1, 2, -1, -3, 4, -6};

		splitArray.splitSwappingRecursive(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	@Test
	public void shouldMoveNegativeElementsToTheLeftWithWithMoreNegativeElementsTwoPointersRecursive()
	{
		int[] array = {1, -2, -1, -3, 4, -6};

		splitArray.splitSwappingRecursive(array);

		assertNegativeElementsAreBeforePositiveOnes(array);
	}

	private void assertNegativeElementsAreBeforePositiveOnes(int[] array)
	{
		if (array.length != 0)
		{
			boolean foundPositiveNumbers = false;
			for (int element : array)
			{
				if (foundPositiveNumbers)
				{
					assertTrue(element >= 0);
				}
				else if (element >= 0)
				{
					foundPositiveNumbers = true;
				}
			}
		}
	}
}
