package algorithem.problem70;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseOrderOfBinaryNumberTest
{

	private ReverseOrderOfBinaryNumber reverseOrder;

	@Before
	public void setUp()
	{
		reverseOrder = new ReverseOrderOfBinaryNumber();
	}

	@Test
	public void shouldReturnZeroIfInputIsZero()
	{
		int input = 0; // 0

		int result = reverseOrder.reverse(input);

		assertEquals(0, result); // 0
	}

	@Test
	public void shouldReturnOneIfInputIsOne()
	{
		int input = 1; // 1

		int result = reverseOrder.reverse(input);

		assertEquals(1, result); // 1
	}

	@Test
	public void shouldReturnTheSameNumberIfIsFullOfOnes()
	{
		int input = 7; // 111

		int result = reverseOrder.reverse(input);

		assertEquals(7, result); // 11
	}

	@Test
	public void shouldReturnBinaryNumberOrder()
	{
		int input = 13; // 1101

		int result = reverseOrder.reverse(input);

		assertEquals(11, result); // 1011
	}
}
