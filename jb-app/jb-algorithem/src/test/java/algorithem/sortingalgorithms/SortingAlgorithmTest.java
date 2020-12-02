package algorithem.sortingalgorithms;

import algorithem.sortingalgorithm.SortingAlgorithm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Abstract test used as base test suit for every sorting algorithm in this repository. This is a
 * testing technique used when you want to test the same behaviour in different implementations and
 * provide some additional test case if needed in the test class which extends this base suit test.
 * SortingAlgorithmTest extensions will have to implement "getSortingAlgorithm" method and provide
 * an instance of any SortingAlgorithm implementation.
 */
public abstract class SortingAlgorithmTest
{

	private SortingAlgorithm sortingAlgorithm;

	public abstract SortingAlgorithm getSortingAlgorithm();

	@Before
	public void setUp()
	{
		sortingAlgorithm = getSortingAlgorithm();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		sortingAlgorithm.sort(null);
	}

	@Test
	public void shouldNotModifyArrayIfIsAlreadySorted()
	{
		int[] input = {1, 2, 3, 4};

		sortingAlgorithm.sort(input);

		int[] expectedArray = {1, 2, 3, 4};
		assertArrayEquals(expectedArray, input);
	}

	@Test
	public void shouldSortArrayWhenTheInputDataIsInDescendingOrder()
	{
		int[] input = {4, 3, 2, 1};

		sortingAlgorithm.sort(input);

		int[] expectedArray = {1, 2, 3, 4};
		assertArrayEquals(expectedArray, input);
	}

	@Test
	public void shouldSortArrayPartiallySorted()
	{
		int[] input = {1, 2, 4, 3};

		sortingAlgorithm.sort(input);

		int[] expectedArray = {1, 2, 3, 4};
		assertArrayEquals(expectedArray, input);
	}

	@Test
	public void shouldSortArray()
	{
		int[] input = {3, 4, 1, 2};

		sortingAlgorithm.sort(input);

		int[] expectedArray = {1, 2, 3, 4};
		assertArrayEquals(expectedArray, input);
	}
}
