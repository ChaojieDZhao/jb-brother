package algorithem.problem41;

/**
 * Given a matrix of integers, can you write a method to go through this matrix using an spiral
 * movement?
 */
public class GoThroughMatrixInSpiral
{

	/**
	 * Iterative solution to this problem. The complexity order in time and space terms is O(N*M)
	 * where N and M is the size of the matrix.
	 * <p>
	 * We are going to use a while to reduce the diameter of the circle iteration by iteration.
	 * X and Y variables are going to be used to access to the matrix.
	 * <p>
	 * Where m == 1 we are in an special case where m and n are odds.
	 * <p>
	 * Inside the main loop we are going to use fors to move pointers in different 4 directions.
	 * <p>
	 * At the end of the main loop you'll have to update your x,y pointers and the size of the matrix
	 * reduced by 2.
	 * <p>
	 * The size of the matrix is going to be modified and used as diameter.
	 */
	public int[] go(int[][] matrix)
	{
		if (matrix == null)
		{
			throw new IllegalArgumentException("You can't pass a null insantece as input.");
		}
		if (matrix.length == 0)
		{
			return new int[0];
		}

		//Calculate sizes
		int m = matrix.length;
		int n = matrix[0].length;
		int resultIndex = 0;
		int[] result = new int[m * n];

		int x = 0;
		int y = 0;

		//We are going to iterate over m and n subtracting 2 in each iteration.
		while (m > 0 && n > 0)
		{

			//If fileDownload row/column left, no circle can be formed
			//Special cases where the size of the matrix is odd
			if (m == 1)
			{
				for (int i = 0; i < n; i++)
				{
					result[resultIndex++] = matrix[x][y];
					y++;
				}
				break;
			}
			else if (n == 1)
			{
				for (int i = 0; i < m; i++)
				{
					result[resultIndex++] = matrix[x][y];
					x++;
				}
				break;
			}

			//Process a circle

			//top - move right. Move n positions -1 to the right
			for (int i = 0; i < n - 1; i++)
			{
				result[resultIndex++] = matrix[x][y];
				y++;
			}

			//right - move down. Move n positions -1 to down.
			for (int i = 0; i < m - 1; i++)
			{
				result[resultIndex++] = matrix[x][y];
				x++;
			}

			//bottom - move left. Move n positions -1 left
			for (int i = 0; i < n - 1; i++)
			{
				result[resultIndex++] = matrix[x][y];
				y--;
			}

			//left - move up. ove n postitions .1 up
			for (int i = 0; i < m - 1; i++)
			{
				result[resultIndex++] = matrix[x][y];
				x--;
			}

			//Increment x and y pointers.
			x++;
			y++;
			//Update sizes to reduce the diameter.
			m = m - 2;
			n = n - 2;
		}
		return result;
	}
}
