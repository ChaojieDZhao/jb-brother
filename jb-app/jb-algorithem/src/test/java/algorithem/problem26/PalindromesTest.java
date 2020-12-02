package algorithem.problem26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromesTest
{

	private Palindromes palindromes;

	@Before
	public void setUp()
	{
		palindromes = new Palindromes();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStrings()
	{
		palindromes.evaluate(null);
	}

	@Test
	public void shouldReturnTrueIfInputStringIsEmpty()
	{
		assertTrue(palindromes.evaluate(""));
	}

	@Test
	public void shouldReturnFalseIfInputIsPalindrome()
	{
		assertFalse(palindromes.evaluate("pedro"));
	}

	@Test
	public void shouldReturnTrueIfInputIsPalindrome()
	{
		assertTrue(palindromes.evaluate("ana"));
	}

}
