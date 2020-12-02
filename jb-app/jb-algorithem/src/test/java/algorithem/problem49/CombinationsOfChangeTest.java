package algorithem.problem49;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CombinationsOfChangeTest
{

	private CombinationsOfChange combinationsOfChange;

	@Before
	public void setUp()
	{
		combinationsOfChange = new CombinationsOfChange();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		combinationsOfChange.get(null, 7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptValueToChangeMinorOrEqualsToZero()
	{
		combinationsOfChange.get(new int[9], -3);
	}

	@Test
	public void shouldReturnAnEmptyCollectionIfArrayOfMoneysIsEmpty()
	{
		List<List<Integer>> combinations = combinationsOfChange.get(new int[0], 1);

		assertTrue(combinations.isEmpty());
	}

	@Test
	public void shouldReturnEveryCombinationOfChange()
	{
		int[] moneys = {5, 10, 15, 50};

		List<List<Integer>> combinations = combinationsOfChange.get(moneys, 60);

		assertContainsChange(combinations, new Integer[] {50, 10}, new Integer[] {15, 15, 15, 15},
			new Integer[] {10, 10, 10, 10, 10, 10},
			new Integer[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5});
	}

	private void assertContainsChange(List<List<Integer>> expectedCombination,
		Integer[]... combinations)
	{
		assertEquals(expectedCombination.size(), combinations.length);

		for (int i = 0; i < expectedCombination.size(); i++)
		{
			List<Integer> combination = expectedCombination.get(i);
			assertEquals(combinations[i].length, combination.size());

			for (int j = 0; j < combination.size(); j++)
			{
				assertEquals(combinations[i][j], combination.get(j));
			}
		}
	}
}

