package algorithem.problem12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MoveZerosInArrayTest
{

	private MoveZerosInArray moveZeros;

	@Before
	public void setUp()
	{
		moveZeros = new MoveZerosInArray();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysSorting()
	{
		moveZeros.moveSorting(null);
	}

	@Test
	public void shouldWorkWithAnEmptyArraySorting()
	{
		int[] array = new int[0];

		moveZeros.moveSorting(array);

		assertArrayEquals(new int[0], array);
	}

	@Test
	public void shouldWorkWithPositiveNegativeZerosArraySorting()
	{
		int[] array = {-1, 0, 2, 4, 0, -3, -5, 0, 6, -3, 0};

		moveZeros.moveSorting(array);

		assertZerosAtRight(array);
	}

	@Test
	public void shouldOrganizeAnArrayFullOfZerosSorting()
	{
		int[] array = {0, 0, 0, 0, 0};

		moveZeros.moveUsingTwoPointers(array);

		int[] expected = {0, 0, 0, 0, 0};
		assertArrayEquals(expected, array);
	}

	@Test
	public void shouldOrganizeAnArrayFullOfNonZerosSorting()
	{
		int[] array = {1, 1, 1, 1, 1};

		moveZeros.moveUsingTwoPointers(array);

		int[] expected = {1, 1, 1, 1, 1};
		assertArrayEquals(expected, array);
	}

	@Test
	public void shouldOrganizeAnArrayWithZerosAndNonPositiveIntegersSorting()
	{
		int[] array = {1, 0, 2, 3, 0};

		moveZeros.moveUsingTwoPointers(array);

		assertZerosAtRight(array);
	}

	@Test
	public void shouldOrganizeAnArrayWithZerosPositiveAndNegativeIntegersSorting()
	{
		int[] array = {1, 0, 2, -3, 0, 0, 0, 0, -1};

		moveZeros.moveUsingTwoPointers(array);

		assertZerosAtRight(array);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysWithTwoPointers()
	{
		moveZeros.moveUsingTwoPointers(null);
	}

	@Test
	public void shouldWorkWithAnEmptyArrayWithTwoPointers()
	{
		int[] array = new int[0];

		moveZeros.moveUsingTwoPointers(array);

		assertArrayEquals(new int[0], array);
	}

	@Test
	public void shouldOrganizeAnArrayFullOfZerosWithTwoPointers()
	{
		int[] array = {0, 0, 0, 0, 0};

		moveZeros.moveUsingTwoPointers(array);

		int[] expected = {0, 0, 0, 0, 0};
		assertArrayEquals(expected, array);
	}

	@Test
	public void shouldOrganizeAnArrayFullOfNonZerosWithTwoPointers()
	{
		int[] array = {1, 1, 1, 1, 1};

		moveZeros.moveUsingTwoPointers(array);

		int[] expected = {1, 1, 1, 1, 1};
		assertArrayEquals(expected, array);
	}

	@Test
	public void shouldOrganizeAnArrayWithZerosAndNonPositiveIntegersWithTwoPointers()
	{
		int[] array = {1, 0, 2, 3, 0};

		moveZeros.moveUsingTwoPointers(array);

		assertZerosAtRight(array);
	}

	@Test
	public void shouldOrganizeAnArrayWithZerosPositiveAndNegativeIntegersWithTwoPointers()
	{
		int[] array = {1, 0, 2, -3, 0, 0, 0, 0, -1};

		moveZeros.moveUsingTwoPointers(array);

		assertZerosAtRight(array);
	}

	private void assertZerosAtRight(int[] array)
	{
		boolean zeroFounded = false;
		for (int i : array)
		{
			if (zeroFounded)
			{
				assertEquals(0, i);
			}
			else if (i == 0)
			{
				zeroFounded = true;
			}
		}
	}
}
