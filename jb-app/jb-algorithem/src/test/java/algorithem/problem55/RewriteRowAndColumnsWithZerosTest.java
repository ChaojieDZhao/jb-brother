package algorithem.problem55;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RewriteRowAndColumnsWithZerosTest
{

	private RewriteRowAndColumnsWithZeros rewriteMatrix;

	@Before
	public void setUp()
	{
		rewriteMatrix = new RewriteRowAndColumnsWithZeros();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullMatrix()
	{
		rewriteMatrix.rewrite(null);
	}

	@Test
	public void shouldRewritePositionsEvenWithAOneDimensionMatrix()
	{
		int[][] matrix = {{1, 0}, {}};

		rewriteMatrix.rewrite(matrix);

		int[][] expectedMatrix = {{0, 0}, {}};
		assertArrayEquals(expectedMatrix, matrix);
	}

	@Test
	public void shouldNotRewritePositionsWithNonZeroElements()
	{
		int[][] matrix = {{1, 1}, {1, 1}};

		rewriteMatrix.rewrite(matrix);

		int[][] expectedMatrix = {{1, 1}, {1, 1}};
		assertArrayEquals(expectedMatrix, matrix);
	}

	@Test
	public void hsouldRewriteJustCenterRowAndCoulmnWithZeros()
	{
		int[][] matrix = {
			{1, 1, 1}, {1, 0, 1}, {1, 1, 1}
		};

		rewriteMatrix.rewrite(matrix);

		int[][] expectedMatrix = {
			{1, 0, 1}, {0, 0, 0}, {1, 0, 1}
		};
		assertArrayEquals(expectedMatrix, matrix);
	}

	@Test
	public void shouldRewriteRowAndColumnsWithZeros()
	{
		int[][] matrix = {
			{1, 1, 0}, {1, 0, 1}, {1, 1, 1}
		};

		rewriteMatrix.rewrite(matrix);

		int[][] expectedMatrix = {
			{0, 0, 0}, {0, 0, 0}, {1, 0, 0}
		};
		assertArrayEquals(expectedMatrix, matrix);
	}
}
