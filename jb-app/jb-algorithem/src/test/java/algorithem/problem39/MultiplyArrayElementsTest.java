package algorithem.problem39;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MultiplyArrayElementsTest
{

	private MultiplyArrayElements multiplyArrayElements;

	@Before
	public void setUp()
	{
		multiplyArrayElements = new MultiplyArrayElements();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		multiplyArrayElements.multiply(null);
	}

	@Test
	public void shouldSupportEmptyArrays()
	{
		int[] array = new int[0];

		int[] result = multiplyArrayElements.multiply(array);

		assertEquals(0, result.length);
	}

	@Test
	public void shouldDoNothingIfTheArrayWithJustOneElement()
	{
		int[] array = {1};

		int[] result = multiplyArrayElements.multiply(array);

		int[] expected = {1};
		assertArrayEquals(expected, result);
	}

	@Test
	public void shouldMultiplyEveryElementByTheRest()
	{
		int[] array = {1, 2, 3};

		int[] result = multiplyArrayElements.multiply(array);

		int[] expected = {6, 3, 2};
		assertArrayEquals(expected, result);
	}
}
