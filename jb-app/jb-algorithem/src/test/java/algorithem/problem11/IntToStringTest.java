package algorithem.problem11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntToStringTest
{

	private IntToString intToString;

	@Before
	public void setUp()
	{
		intToString = new IntToString();
	}

	@Test
	public void shouldTransformZero()
	{
		assertEquals("0", intToString.transform(0));
	}

	@Test
	public void shouldTransformPositiveIntegersWithJustOneDigit()
	{
		assertEquals("7", intToString.transform(7));
	}

	@Test
	public void shouldTransformNegativeIntegersWithJustOneDigit()
	{
		assertEquals("-4", intToString.transform(-4));
	}

	@Test
	public void shouldTransformPositiveIntegersWithMoreThanOneDigits()
	{
		assertEquals("24", intToString.transform(24));
	}

	@Test
	public void shouldTransformNegativeIntegersWithMoreThanOneDigits()
	{
		assertEquals("-15", intToString.transform(-15));
	}
}
