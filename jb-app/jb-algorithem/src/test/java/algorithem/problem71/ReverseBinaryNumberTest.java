package algorithem.problem71;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseBinaryNumberTest
{

	private ReverseBinaryNumber reverseBinaryNumber;

	@Before
	public void setUp()
	{
		reverseBinaryNumber = new ReverseBinaryNumber();
	}

	@Test
	public void shouldReturnOneIfInputIsZero()
	{
		int input = 0; // 0

		int result = reverseBinaryNumber.reverse(input);

		assertEquals(1, result); // 1
	}

	@Test
	public void shouldReturnZeroIfInputIsOne()
	{
		int input = 1; // 1

		int result = reverseBinaryNumber.reverse(input);

		assertEquals(0, result); // 0
	}

	@Test
	public void shouldReturnZeroIfInputIsFullOfOnes()
	{
		int input = 63; // 111111

		int result = reverseBinaryNumber.reverse(input);

		assertEquals(0, result); // 0
	}

	@Test
	public void shouldReturnOneIfInputIsTwo()
	{
		int input = 2; // 10

		int result = reverseBinaryNumber.reverse(input);

		assertEquals(1, result);//01
	}

	@Test
	public void shouldReturnTheReverseBinaryNumber()
	{
		int input = 11; // 1011

		int result = reverseBinaryNumber.reverse(input);

		assertEquals(4, result); // 0100
	}
}
