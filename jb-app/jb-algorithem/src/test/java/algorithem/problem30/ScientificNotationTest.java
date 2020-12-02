package algorithem.problem30;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScientificNotationTest
{

	private ScientificNotation scientificNotation;

	@Before
	public void setUp()
	{
		scientificNotation = new ScientificNotation();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringAsInput()
	{
		scientificNotation.calculate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptMalformedScientificNumbers()
	{
		scientificNotation.calculate("9.18F+09");
	}

	@Test
	public void shouldReturnTheValueOfTheNumber()
	{
		String number = "9.18E+09";

		long result = scientificNotation.calculate(number);

		assertEquals(9180000000L, result);
	}

	@Test
	public void shouldTakeIntoAccountNegativeNumbers()
	{
		String number = "-9.18E+09";

		long result = scientificNotation.calculate(number);

		assertEquals(-9180000000L, result);
	}
}
