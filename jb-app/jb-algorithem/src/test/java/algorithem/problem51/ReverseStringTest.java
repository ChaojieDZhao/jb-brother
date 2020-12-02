package algorithem.problem51;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseStringTest
{

	private ReverseString reverseString;

	@Before
	public void setUp()
	{
		reverseString = new ReverseString();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsIterative()
	{
		reverseString.reverseIterative(null);
	}

	@Test
	public void shouldReturnAnEmptyStringIfTheInputIsEmptyIterative()
	{
		String result = reverseString.reverseIterative("");

		assertEquals("", result);
	}

	@Test
	public void shouldReturnTheSameStringIfTheInputStringContainsJustOneCharIterative()
	{
		String result = reverseString.reverseIterative("a");

		assertEquals("a", result);
	}

	@Test
	public void shouldReverseStringIterative()
	{
		String result = reverseString.reverseIterative("Pedro");

		assertEquals("ordeP", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsRecursive()
	{
		reverseString.reverseRecursive(null);
	}

	@Test
	public void shouldReturnAnEmptyStringIfTheInputIsEmptyRecursive()
	{
		String result = reverseString.reverseRecursive("");

		assertEquals("", result);
	}

	@Test
	public void shouldReturnTheSameStringIfTheInputStringContainsJustOneCharRecursive()
	{
		String result = reverseString.reverseRecursive("a");

		assertEquals("a", result);
	}

	@Test
	public void shouldReverseStringRecursive()
	{
		String result = reverseString.reverseRecursive("Pedro");

		assertEquals("ordeP", result);
	}
}
