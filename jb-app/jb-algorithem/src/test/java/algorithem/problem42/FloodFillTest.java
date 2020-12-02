package algorithem.problem42;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FloodFillTest
{

	private FloodFill floodFill;

	@Before
	public void setUp()
	{
		floodFill = new FloodFill();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstances()
	{
		floodFill.apply(null, 0, 1, 0, 0);
	}

	@Test
	public void shouldPaintSelectedZoneUsingIndicatedColor()
	{
		int[][] picture = {
			{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}
		};
		int colorToReplace = 0;
		int colorToPaint = 1;
		int x = 1;
		int y = 1;

		floodFill.apply(picture, colorToReplace, colorToPaint, x, y);

		int[][] expectedResult = {
			{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}
		};
		assertArrayEquals(expectedResult, picture);
	}

	@Test
	public void shouldNotModifyIfStartPaintingInOnePixelWithTheColorToPaint()
	{
		int[][] picture = {
			{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}
		};
		int colorToReplace = 0;
		int colorToPaint = 1;
		int x = 0;
		int y = 0;

		floodFill.apply(picture, colorToReplace, colorToPaint, x, y);

		int[][] expectedResult = {
			{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}
		};
		assertArrayEquals(expectedResult, picture);
	}
}
