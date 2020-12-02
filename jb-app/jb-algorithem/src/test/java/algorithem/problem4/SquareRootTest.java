package algorithem.problem4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareRootTest
{

	private static final float DELTA = 0.1f;

	private SquareRoot squareRoot;

	@Before
	public void setUp()
	{
		squareRoot = new SquareRoot();
	}

	@Test
	public void squareRootOfFourEqualsToTwo()
	{
		float result = squareRoot.calculateSquareRootIterative(4);

		assertEquals(2, result, DELTA);
	}

	@Test
	public void squareRootOfNineEqualsToThreeIterative()
	{
		float result = squareRoot.calculateSquareRootIterative(9);

		assertEquals(3, result, DELTA);
	}

	@Test
	public void shouldCalculateNonPerfectSquaresIterative()
	{
		float result = squareRoot.calculateSquareRootIterative(7);

		assertEquals(2.64575f, result, DELTA);
	}

	@Test
	public void shouldCalculateSquaresOfTwoDigitsIterative()
	{
		float result = squareRoot.calculateSquareRootIterative(12);

		assertEquals(3.46410f, result, DELTA);
	}

	@Test
	public void squareRootOfFourEqualsToTwoRecursiveIterative()
	{
		float result = squareRoot.calculateSquareRootRecursive(4);

		assertEquals(2, result, DELTA);
	}

	@Test
	public void squareRootOfNineEqualsToThreeRecursive()
	{
		float result = squareRoot.calculateSquareRootRecursive(9);

		assertEquals(3, result, DELTA);
	}

	@Test
	public void shouldCalculateNonPerfectSquaresRecursive()
	{
		float result = squareRoot.calculateSquareRootRecursive(7);

		assertEquals(2.64575f, result, DELTA);
	}

	@Test
	public void shouldCalculateSquaresOfTwoDigitsRecursive()
	{
		float result = squareRoot.calculateSquareRootRecursive(12);

		assertEquals(3.46410f, result, DELTA);
	}
}
