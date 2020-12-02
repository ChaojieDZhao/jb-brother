package algorithem.problem7;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciNumbersTest
{

	private FibonacciNumbers fibonacciNumbers;

	@Before
	public void setUp()
	{
		fibonacciNumbers = new FibonacciNumbers();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeValuesRecursive()
	{
		fibonacciNumbers.getRecursive(-1);
	}

	@Test
	public void firstNumberInFibonacciSequenceIsThreeRecursive()
	{
		assertEquals(1, fibonacciNumbers.getRecursive(1));
	}

	@Test
	public void fourthNumberInFibonacciSequenceIsThreeRecursive()
	{
		assertEquals(5, fibonacciNumbers.getRecursive(5));
	}

	@Test
	public void eleventhNumberInFibonacciSequenceIsRecursive()
	{
		assertEquals(144, fibonacciNumbers.getRecursive(12));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeValuesIterative()
	{
		fibonacciNumbers.getIterative(-1);
	}

	@Test
	public void firstNumberInFibonacciSequenceIsThreeIterative()
	{
		assertEquals(1, fibonacciNumbers.getIterative(1));
	}

	@Test
	public void fourthNumberInFibonacciSequenceIsThreeIterative()
	{
		assertEquals(5, fibonacciNumbers.getIterative(5));
	}

	@Test
	public void eleventhNumberInFibonacciSequenceIsIterative()
	{
		assertEquals(144, fibonacciNumbers.getIterative(12));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativeValuesRecursiveWithCatching()
	{
		fibonacciNumbers.getRecursiveWithCaching(-1);
	}

	@Test
	public void fourthNumberInFibonacciSequenceIsThreeRecursiveWithCatching()
	{
		assertEquals(5, fibonacciNumbers.getRecursiveWithCaching(5));
	}

	@Test
	public void eleventhNumberInFibonacciSequenceIsRecursiveWithCatching()
	{
		assertEquals(144, fibonacciNumbers.getRecursiveWithCaching(12));
	}
}
