package algorithem.problem27;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseSentenceTest
{

	private ReverseSentence reverseSentence;

	@Before
	public void setUp()
	{
		reverseSentence = new ReverseSentence();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput()
	{
		reverseSentence.reverse(null);
	}

	@Test
	public void shouldReturnEmptyIfInputIsEmpty()
	{
		assertEquals("", reverseSentence.reverse(""));
	}

	@Test
	public void shouldReverseOneSentenceWithJustOneWord()
	{
		String input = "pedro";

		String result = reverseSentence.reverse(input);

		assertEquals("pedro", result);
	}

	@Test
	public void shouldReverseSentenceWithMoreThanOneWord()
	{
		String input = "pedro vicente gómez";

		String result = reverseSentence.reverse(input);

		assertEquals("gómez vicente pedro", result);
	}
}
