package algorithem.problem35;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsteriskRegularExpressionTest
{

	private AsteriskRegularExpression asteriskRegularExpression;

	@Before
	public void setUp()
	{
		asteriskRegularExpression = new AsteriskRegularExpression();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstancesAsInput()
	{
		asteriskRegularExpression.evaluate(null, null);
	}

	@Test
	public void shouldReturnAnEmptyArrayIfTheArrayIsEmpty()
	{
		String pattern = "p*d";
		String[] words = {};

		String[] result = asteriskRegularExpression.evaluate(words, pattern);

		assertEquals(0, result.length);
	}

	@Test
	public void shouldReturnAnEmptyArrayIfPatternDoesNotMatch()
	{
		String pattern = "p*d";
		String[] words = {"ana", "test1", "test2"};

		String[] result = asteriskRegularExpression.evaluate(words, pattern);

		assertEquals(0, result.length);
	}

	@Test
	public void shouldReturnAnArrayWithMatches()
	{
		String pattern = "p*d";
		String[] words = {"pod", "piid", "pat", "por", "pwd"};

		String[] result = asteriskRegularExpression.evaluate(words, pattern);

		String[] expectedResult = {"pod", "piid", "pwd"};
		assertArrayEquals(expectedResult, result);
	}
}
