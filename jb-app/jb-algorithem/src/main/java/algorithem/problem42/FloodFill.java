package algorithem.problem42;

/**
 * Given a matrix full of 1's and 0's can you write a method to fill part of the matrix using a
 * number?
 */
public class FloodFill
{

	/**
	 * Method created to avoid IndexOutOfBoundExceptions. This method return -1 if you try to access
	 * an invalid position.
	 */
	private static int getValueAt(int[][] picture, int x, int y)
	{
		if (x < 0 || y < 0 || x > picture.length || y > picture[x].length)
		{
			return -1;
		}
		else
		{
			return picture[x][y];
		}
	}

	/**
	 * Implementation of a tail recursive flood fill algorithm to solve this problem. Flood fill,
	 * also called seed fill, is an algorithm that determines the area connected to a given node in a
	 * multi-dimensional array. It is used in the "bucket" fill tool of paint programs to fill
	 * connected, similarly-colored areas with a different color, and in games such as Go and
	 * Minesweeper for determining which pieces are cleared. When applied on an image to fill a
	 * particular bounded area with color, it is also known as boundary fill.
	 * <p>
	 * The complexity order of this algorithm in space terms is O(1) because we are not using any
	 * additional data structure to store data temporally. In time termsn the complexity order is
	 * O(N) where N is the number of pixels to change color.
	 */
	public void apply(int[][] picture, int colorToReplace, int colorToPaint, int x, int y)
	{
		validatePicture(picture);

		int currentColor = getValueAt(picture, x, y);
		if (currentColor == colorToReplace)
		{
			picture[x][y] = colorToPaint;
			apply(picture, colorToReplace, colorToPaint, x + 1, y);
			apply(picture, colorToReplace, colorToPaint, x - 1, y);
			apply(picture, colorToReplace, colorToPaint, x, y + 1);
			apply(picture, colorToReplace, colorToPaint, x, y - 1);
		}
	}

	private void validatePicture(int[][] picture)
	{
		if (picture == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance as picture");
		}
	}
}
