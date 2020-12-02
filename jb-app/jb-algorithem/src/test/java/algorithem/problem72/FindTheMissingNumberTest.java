package algorithem.problem72;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindTheMissingNumberTest
{

	private FindTheMissingNumber findMissingNumber;

	@Before
	public void setUp()
	{
		findMissingNumber = new FindTheMissingNumber();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstances()
	{
		findMissingNumber.find(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmptyArrays()
	{
		findMissingNumber.find(new int[0]);
	}

	@Test
	public void shouldFindMissingNumber()
	{
		int[] numbers = {0, 1, 2, 4};

		int missingNumber = findMissingNumber.find(numbers);

		assertEquals(3, missingNumber);
	}
}
