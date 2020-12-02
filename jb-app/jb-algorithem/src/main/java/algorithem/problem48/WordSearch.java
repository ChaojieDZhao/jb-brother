package algorithem.problem48;

/**
 * Given a matrix representing a word search, can you write a method to return true if a word
 * passed
 * as argument is inside the word search?
 */
public class WordSearch
{

	/**
	 * This could be better if you put every check if the same line with the return because
	 * you are going to check in different directions until you find it instead of check the 8
	 * different possibilities.
	 */
	private static boolean isWordInWordSearchInner(char[][] wordSearch, String word, int x, int y)
	{
		if (word.isEmpty())
		{
			return true;
		}
		else
		{
			boolean firstLetter = matches(wordSearch, x, y, word.charAt(0));
			if (firstLetter)
			{
				boolean left = isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y);
				boolean right = isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y);
				boolean bottom = isWordInWordSearchInner(wordSearch, word.substring(1), x, y + 1);
				boolean top = isWordInWordSearchInner(wordSearch, word.substring(1), x, y - 1);
				boolean topLeft = isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y - 1);
				boolean topRight = isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y - 1);
				boolean bottomLeft = isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y + 1);
				boolean bottomRight = isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y + 1);

				return left || right || bottom || top || topLeft || topRight || bottomLeft || bottomRight;
			}
			return false;
		}
	}

	private static boolean matches(char[][] wordSearch, int x, int y, char c)
	{
		if (x < 0 || x >= wordSearch.length || y < 0 || y >= wordSearch[x].length)
		{
			return false;
		}
		else
		{
			return wordSearch[x][y] == c;
		}
	}

	/**
	 * Combination of iterative or recursive algorithm to solve this problem.The complexity order of
	 * this algorithm is O(N*N*M) where N is the number of elements in every row of the matrix and M
	 * is the number of chars in the word to search.
	 */
	public boolean contains(char[][] wordSearch, String word)
	{
		validateInputData(wordSearch, word);

		for (int i = 0; i < wordSearch.length; i++)
		{
			for (int j = 0; j < wordSearch[i].length; j++)
			{
				if (matches(wordSearch, i, j, word.charAt(0)))
				{
					if (isWordInWordSearchInner(wordSearch, word, i, j))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	private void validateInputData(char[][] wordSearch, String word)
	{
		if (word == null || wordSearch == null)
		{
			throw new IllegalArgumentException("You can't pass null instances as parameter.");
		}
	}
}
