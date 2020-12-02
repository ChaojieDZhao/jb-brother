package algorithem.problem33;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleRegularExpressionTest
{

	private SimpleRegularExpression simpleRegularExpression;

	@Before
	public void setUp()
	{
		simpleRegularExpression = new SimpleRegularExpression();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput()
	{
		simpleRegularExpression.evaluate(null, null);
	}

	@Test
	public void shouldReturnFalseIfInputDoesNotMatchesAndDoesNotContainKeyElements()
	{
		String input = "aaa";
		String regularExpression = "a";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueIfInputMatchesAndDoesNotContainKeyElements()
	{
		String input = "aaa";
		String regularExpression = "aaa";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertTrue(result);
	}

	@Test
	public void shouldReturnTrueIfMatchesUsingAsterisk()
	{
		String input = "aaa";
		String regularExpression = "a*";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertTrue(result);
	}

	@Test
	public void shouldReturnFalseIfDoesNotMatchUsingAsterisk()
	{
		String input = "aaa";
		String regularExpression = "b*";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueIfMatchesUsingDot()
	{
		String input = "aa";
		String regularExpression = "a.";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertTrue(result);
	}

	@Test
	public void shouldReturnFalseIfDoesNotMatchUsingDot()
	{
		String input = "aaa";
		String regularExpression = "a.";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueIfMatchesUsingAsteriskAndDot()
	{
		String input = "aaa";
		String regularExpression = ".a*";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertTrue(result);
	}

	@Test
	public void shouldReturnFalseIfDoesNotMatchUsingAsteriskAndDot()
	{
		String input = "aaa";
		String regularExpression = ".b*";

		boolean result = simpleRegularExpression.evaluate(input, regularExpression);

		assertFalse(result);
	}
}
