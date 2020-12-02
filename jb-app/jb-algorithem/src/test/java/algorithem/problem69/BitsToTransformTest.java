package algorithem.problem69;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitsToTransformTest
{

	private BitsToTransform bitsToTransform;

	@Before
	public void setUp()
	{
		bitsToTransform = new BitsToTransform();
	}

	@Test
	public void shouldReturnZeroIfBothNumbersAreEquals()
	{
		int numA = 7;
		int numB = 7;

		int numberOfDifferentBits = bitsToTransform.calculate(numA, numB);

		assertEquals(0, numberOfDifferentBits);
	}

	@Test
	public void shouldClaculateHappyCase()
	{
		int numA = 5; // 101
		int numB = 1; // 001

		int numberOfDifferentBits = bitsToTransform.calculate(numA, numB);

		assertEquals(1, numberOfDifferentBits);
	}

	@Test
	public void shouldReturnTheNumberOfDifferentBitsWhenNumAIsLower()
	{
		int numA = 78; // 1001110
		int numB = 99; // 1100011

		int numberOfDifferentBits = bitsToTransform.calculate(numA, numB);

		assertEquals(4, numberOfDifferentBits);
	}

	@Test
	public void shouldReturnTheNumberOfDifferentBitsWhenNumAIsGreater()
	{
		int numA = 99; // 1100011
		int numB = 78; // 1001110

		int numberOfDifferentBits = bitsToTransform.calculate(numA, numB);

		assertEquals(4, numberOfDifferentBits);
	}
}
