package algorithem.problem45;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindNthMostRepeatedElementTest
{

	private FindNthMostRepeatedElement findNthMostRepeatedElement;

	@Before
	public void setUp()
	{
		findNthMostRepeatedElement = new FindNthMostRepeatedElement();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		findNthMostRepeatedElement.find(null, 9);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptInputValuesMinorThanZero()
	{
		findNthMostRepeatedElement.find(new int[0], 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfThereAreNoElementsRepeatedNTimesInTheArrayPassedAsArgument()
	{
		int[] numbers = {1, 2, 3};

		findNthMostRepeatedElement.find(numbers, 2);
	}

	@Test
	public void shouldFindNthMostRepeatedElement()
	{
		int[] numbers = {1, 1, 2, 3, 4, 5, 2, 2, 2, 4, 4, 6, 7, 4, 9, 214, 4, 5,};

		int result = findNthMostRepeatedElement.find(numbers, 2);

		assertEquals(1, result);
	}
}
