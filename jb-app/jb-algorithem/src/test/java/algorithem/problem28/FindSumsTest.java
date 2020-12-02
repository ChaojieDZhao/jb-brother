package algorithem.problem28;

import algorithem.pair.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class FindSumsTest
{

	private FindSums findSums;

	@Before
	public void setUp()
	{
		findSums = new FindSums();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		findSums.find(null, 2);
	}

	@Test
	public void shouldReturnAnEmptyResultIfTheArrayIsEmpty()
	{
		int[] inputArray = {};

		List<Pair<Integer, Integer>> result = findSums.find(inputArray, 3);

		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldFindEveryPair()
	{
		int[] inputArray = {5, 2, 6, 1, 9};
		int inputValue = 7;

		List<Pair<Integer, Integer>> result = findSums.find(inputArray, inputValue);

		assertTrue(result.contains(new Pair<Integer, Integer>(5, 2)));
		assertTrue(result.contains(new Pair<Integer, Integer>(6, 1)));
	}

	@Test
	public void shouldWorkWithNegativeIntegersAsInput()
	{
		int[] inputArray = {5, 2, -17, 6, 1, 9};
		int inputValue = -8;

		List<Pair<Integer, Integer>> result = findSums.find(inputArray, inputValue);

		assertTrue(result.contains(new Pair<Integer, Integer>(-17, 9)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysLinear()
	{
		findSums.findLinearComplexityOrder(null, 2);
	}

	@Test
	public void shouldReturnAnEmptyResultIfTheArrayIsEmptyLinear()
	{
		int[] inputArray = {};

		List<Pair<Integer, Integer>> result = findSums.findLinearComplexityOrder(inputArray, 3);

		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldFindEveryPairLinear()
	{
		int[] inputArray = {5, 2, 6, 1, 9};
		int inputValue = 7;

		List<Pair<Integer, Integer>> result =
			findSums.findLinearComplexityOrder(inputArray, inputValue);

		assertTrue(result.contains(new Pair<Integer, Integer>(2, 5)));
		assertTrue(result.contains(new Pair<Integer, Integer>(1, 6)));
	}

	@Test
	public void shouldWorkWithNegativeIntegersAsInputLinear()
	{
		int[] inputArray = {5, 2, -17, 6, 1, 9};
		int inputValue = -8;

		List<Pair<Integer, Integer>> result =
			findSums.findLinearComplexityOrder(inputArray, inputValue);

		assertTrue(result.contains(new Pair<Integer, Integer>(9, -17)));
	}
}
