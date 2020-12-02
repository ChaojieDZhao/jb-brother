package algorithem.problem20;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplicationWithoutMultiplyTest
{

	private MultiplicationWithoutMultiply multiply;

	@Before
	public void setUp()
	{
		multiply = new MultiplicationWithoutMultiply();
	}

	@Test
	public void anyNumberMultipliedByZeroIsEqualsToZero()
	{
		assertEquals(0, multiply.calculate(0, 100));
	}

	@Test
	public void anyNumberMultipliedByOneShouldReturnTheSameNumber()
	{
		assertEquals(7, multiply.calculate(1, 7));
	}

	@Test
	public void fiveTimesElevenEqualsToFiftyFive()
	{
		assertEquals(55, multiply.calculate(5, 11));
	}

	@Test
	public void shouldTakeIntoAccountNegativeNumbers()
	{
		assertEquals(-14, multiply.calculate(-2, 7));
	}
}
