package algorithem.problem32;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsTest
{

	private Contains contains;

	@Before
	public void setUp()
	{
		contains = new Contains();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput()
	{
		contains.evaluate(null, null);
	}

	@Test
	public void shouldReturnTrueIfSecondStringContainsFirstString()
	{
		String word1 = "dro";
		String word2 = "Pedro";

		boolean result = contains.evaluate(word1, word2);

		assertTrue(result);
	}

	@Test
	public void shouldReturnFalseIfSecondStringDoesNotContainFirstString()
	{
		String word1 = "ana";
		String word2 = "Pedro";

		boolean result = contains.evaluate(word1, word2);

		assertFalse(result);
	}

	@Test
	public void shouldBeCaseSensitive()
	{
		String word1 = "PE";
		String word2 = "Pedro";

		boolean result = contains.evaluate(word1, word2);

		assertFalse(result);
	}
}
