package algorithem.problem40;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveElementsToPositionsTest
{

	private MoveElementsToPositions moveElementsToPosition;

	@Before
	public void setUp()
	{
		moveElementsToPosition = new MoveElementsToPositions();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysAsInput()
	{
		moveElementsToPosition.move(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptAnyNumberGreaterThanArrayLengthOrMinorThanOne()
	{
		int[] input = {1, 4};

		moveElementsToPosition.move(input);
	}

	@Test
	public void shouldMoveElementsToTheCorrectPosition()
	{
		int[] input = {2, 3, 1, 0};

		moveElementsToPosition.move(input);

		assertNumbersAreInTheCorrectPosition(input);
	}

	private void assertNumbersAreInTheCorrectPosition(int[] input)
	{
		for (int i = 0; i < input.length; i++)
		{
			assertEquals(i, input[i]);
		}
	}
}
