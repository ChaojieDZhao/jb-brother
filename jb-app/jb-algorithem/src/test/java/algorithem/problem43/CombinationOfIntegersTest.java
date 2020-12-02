package algorithem.problem43;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class CombinationOfIntegersTest
{

	private CombinationOfIntegers combinationOfIntegers;

	@Before
	public void setUp()
	{
		combinationOfIntegers = new CombinationOfIntegers();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstancesAsInput()
	{
		combinationOfIntegers.calculate(null);
	}

	@Test
	public void shouldJustContainEmptySetIfInputSetIsEmpty()
	{
		Set<Integer> input = new HashSet<Integer>();

		Set<Set<Integer>> result = combinationOfIntegers.calculate(input);

		assertSetContainsSet(result);
	}

	@Test
	public void shouldCalculateEveryCombination()
	{
		Set<Integer> input = new HashSet<Integer>();
		input.add(1);
		input.add(2);

		Set<Set<Integer>> result = combinationOfIntegers.calculate(input);

		assertSetContainsSet(result);
		assertSetContainsSet(result, 1);
		assertSetContainsSet(result, 2);
		assertSetContainsSet(result, 1, 2);
	}

	private void assertSetContainsSet(Set<Set<Integer>> result, int... elements)
	{
		Set<Integer> expected = new LinkedHashSet<Integer>();
		for (int i : elements)
		{
			expected.add(i);
		}
		assertTrue(result.contains(expected));
	}
}
