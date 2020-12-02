package algorithem.problem34;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DotRegularExpressionTest
{

	private DotRegularExpression dotRegularExpression;

	@Before
	public void setUp()
	{
		dotRegularExpression = new DotRegularExpression();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrayOrPatternAsInput()
	{
		dotRegularExpression.evaluate(null, null);
	}

	@Test
	public void shouldReturnAnEmptyArrayIfTheArrayIsEmpty()
	{
		String pattern = "p.d";
		String[] words = {};

		String[] result = dotRegularExpression.evaluate(words, pattern);

		assertEquals(0, result.length);
	}

	@Test
	public void shouldReturnAnEmptyArrayIfPatternDoesNotMatch()
	{
		String pattern = "p.d";
		String[] words = {"ana", "test1", "test2"};

		String[] result = dotRegularExpression.evaluate(words, pattern);

		assertEquals(0, result.length);
	}

	@Test
	public void shouldReturnAnArrayWithMatches()
	{
		String pattern = "p.d";
		String[] words = {"pod", "pid", "pat", "por", "pwd"};

		String[] result = dotRegularExpression.evaluate(words, pattern);

		String[] expectedResult = {"pod", "pid", "pwd"};
		assertArrayEquals(expectedResult, result);
	}
}
