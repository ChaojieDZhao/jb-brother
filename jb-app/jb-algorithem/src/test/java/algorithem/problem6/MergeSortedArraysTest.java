package algorithem.problem6;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortedArraysTest
{

	private MergeSortedArrays mergeSortedArrays;

	@Before
	public void setUp()
	{
		mergeSortedArrays = new MergeSortedArrays();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		mergeSortedArrays.merge(null, null);
	}

	@Test
	public void shouldConcatenateArraysIfThereArentArrayCollisions()
	{
		int a1[] = {1, 2, 3, 4, 5};
		int a2[] = {7, 8, 9};

		int[] result = mergeSortedArrays.merge(a1, a2);

		int[] expectedArray = {1, 2, 3, 4, 5, 7, 8, 9};
		assertArrayEquals(expectedArray, result);
	}

	@Test
	public void shouldMergeArraysWithCollisions()
	{
		int a1[] = {1, 2, 3, 4, 9, 10, 11};
		int a2[] = {5, 6, 7};

		int[] result = mergeSortedArrays.merge(a1, a2);

		int[] expectedArray = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11};
		assertArrayEquals(expectedArray, result);
	}
}
