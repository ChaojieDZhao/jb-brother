package algorithem.problem54;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RotateMatrixTest
{

	private RotateMatrix rotateMatrix;

	@Before
	public void setUp()
	{
		rotateMatrix = new RotateMatrix();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullMatrix()
	{
		rotateMatrix.rotate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptMatrixWithDifferentHeightAndWidth()
	{
		int[][] matrix = new int[6][3];

		rotateMatrix.rotate(matrix);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptAnEmtpyMatrix()
	{
		int[][] matrix = new int[0][0];

		rotateMatrix.rotate(matrix);
	}

	@Test
	public void shouldRotateMatrix()
	{
		int[][] matrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};

		rotateMatrix.rotate(matrix);

		int[][] expectedResult = {{6, 3, 0}, {7, 4, 1}, {8, 5, 2}};
		assertArrayEquals(expectedResult, matrix);
	}
}
