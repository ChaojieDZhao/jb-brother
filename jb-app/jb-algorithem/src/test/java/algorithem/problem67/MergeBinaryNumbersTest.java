package algorithem.problem67;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeBinaryNumbersTest
{

	private MergeBinaryNumbers mergeBinaryNumbers;

	@Before
	public void setUp()
	{
		mergeBinaryNumbers = new MergeBinaryNumbers();
	}

	@Test
	public void shouldMergeBinaryNumbersUsingPointers()
	{
		int n = 1024; //10000000000
		int m = 19; //10011

		int result = mergeBinaryNumbers.merge(n, m, 2, 6);

		int expectedResult = 1100; //10001001100
		assertEquals(expectedResult, result);
	}
}
