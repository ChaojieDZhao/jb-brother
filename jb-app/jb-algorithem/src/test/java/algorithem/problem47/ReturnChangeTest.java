package algorithem.problem47;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReturnChangeTest
{

	private ReturnChange returnChange;

	@Before
	public void setUp()
	{
		returnChange = new ReturnChange();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrayOfMoneys()
	{
		returnChange.calculate(null, 7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmptyArrayOfMoneys()
	{
		returnChange.calculate(new int[0], 7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptZeroAsInputValue()
	{
		returnChange.calculate(new int[0], 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeValuesAsInputValue()
	{
		returnChange.calculate(new int[0], -7);
	}

	@Test
	public void shouldReturnChangeUsingJustTheBiggestMoney()
	{
		int[] moneys = {5, 10, 15, 50};

		List<Integer> change = returnChange.calculate(moneys, 200);

		int[] expectedChange = {50, 50, 50, 50};
		assertChangeEqualsTo(expectedChange, change);
	}

	@Test
	public void shouldReturnChangeUsingMoreThanOneMoney()
	{
		int[] moneys = {5, 10, 15, 50};

		List<Integer> change = returnChange.calculate(moneys, 80);

		int[] expectedChange = {50, 15, 15};
		assertChangeEqualsTo(expectedChange, change);
	}

	private void assertChangeEqualsTo(int[] expectedChange, List<Integer> change)
	{
		assertEquals(expectedChange.length, change.size());
		for (int i = 0; i < expectedChange.length; i++)
		{
			Integer partialChange = expectedChange[i];
			assertEquals(partialChange, change.get(i));
		}
	}
}
