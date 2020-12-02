package algorithem.problem9;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactorialTest
{

	private Factorial factorial;

	@Before
	public void setUp()
	{
		this.factorial = new Factorial();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCalculateFactorialOfNegativeIntegersIterative()
	{
		factorial.getIterative(-1);
	}

	@Test
	public void factorialOfZeroEqualsToOneIterative()
	{
		int result = factorial.getIterative(0);

		assertEquals(1, result);
	}

	@Test
	public void factorialOfFiveEqualsTo120Iterative()
	{
		int result = factorial.getIterative(5);

		assertEquals(120, result);
	}

	@Test
	public void factorialOfSevenEqualsTo5040Iterative()
	{
		int result = factorial.getIterative(7);

		assertEquals(5040, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCalculateFactorialOfNegativeIntegersRecursive()
	{
		factorial.getRecursive(-1);
	}

	@Test
	public void factorialOfZeroEqualsToOneRecursive()
	{
		int result = factorial.getRecursive(0);

		assertEquals(1, result);
	}

	@Test
	public void factorialOfFiveEqualsTo120Recursive()
	{
		int result = factorial.getRecursive(5);

		assertEquals(120, result);
	}

	@Test
	public void factorialOfSevenEqualsTo5040Recursive()
	{
		int result = factorial.getRecursive(7);

		assertEquals(5040, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCalculateFactorialOfNegativeIntegersTailRecursive()
	{
		factorial.getTailRecursive(-1);
	}

	@Test
	public void factorialOfZeroEqualsToOneTailRecursive()
	{
		int result = factorial.getTailRecursive(0);

		assertEquals(1, result);
	}

	@Test
	public void factorialOfFiveEqualsTo120TailRecursive()
	{
		int result = factorial.getTailRecursive(5);

		assertEquals(120, result);
	}

	@Test
	public void factorialOfSevenEqualsTo5040TailRecursive()
	{
		int result = factorial.getTailRecursive(7);

		assertEquals(5040, result);
	}
}
