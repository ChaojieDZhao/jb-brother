package algorithem.problem48;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordSearchTest
{

	private WordSearch wordSearch;

	@Before
	public void setUp()
	{
		wordSearch = new WordSearch();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullWordSearch()
	{
		wordSearch.contains(null, "word");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullWordsToSearchInTheMatrix()
	{
		wordSearch.contains(new char[0][0], null);
	}

	@Test
	public void shouldReturnFalseIfWordSearchIsEmpty()
	{
		char[][] matrix = {
			{'a', 'a', 'c', 'd', 'e'}, {'a', 'd', 'a', 'r', 'c'}, {'a', 'd', 'a', 'c', 'c'},
			{'b', 'a', 'c', 'o', 'o'}
		};

		boolean result = wordSearch.contains(matrix, "pedro");

		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueIfWordToSearchIsInsideWordSearch()
	{
		char[][] matrix = {
			{'a', 'a', 'c', 'd', 'e'}, {'a', 'd', 'a', 'r', 'c'}, {'a', 'd', 'a', 'c', 'c'},
			{'b', 'a', 'c', 'o', 'o'}
		};

		boolean result = wordSearch.contains(matrix, "baco");

		assertTrue(result);
	}
}
