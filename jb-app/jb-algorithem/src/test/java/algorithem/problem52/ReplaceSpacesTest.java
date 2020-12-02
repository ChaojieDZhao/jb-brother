package algorithem.problem52;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ReplaceSpacesTest
{

	private ReplaceSpaces replaceSpaces;

	@Before
	public void setUp()
	{
		replaceSpaces = new ReplaceSpaces();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		replaceSpaces.replace(null);
	}

	@Test
	public void shouldReplaceAllTheContentIfTheArrayIsFullOfSpaces()
	{
		char[] input = new char[6];
		input[0] = ' ';
		input[1] = ' ';

		replaceSpaces.replace(input);

		assertArrayEquals(new char[] {'%', '2', '0', '%', '2', '0'}, input);
	}

	@Test
	public void shouldReplaceSpacesWithTheStatementCharWithJustOneSpace()
	{
		char[] input = new char[8];
		input[0] = 'p';
		input[1] = 'e';
		input[2] = ' ';
		input[3] = 'd';
		input[4] = 'r';
		input[5] = 'o';

		replaceSpaces.replace(input);

		assertArrayEquals(new char[] {'p', 'e', '%', '2', '0', 'd', 'r', 'o'}, input);
	}

	@Test
	public void shouldReplaceSpacesWithTheStatementCharWithJustOneSpaceAtTheEndOfTheContent()
	{
		char[] input = new char[8];
		input[0] = 'p';
		input[1] = 'e';
		input[2] = 'd';
		input[3] = 'r';
		input[4] = 'o';
		input[5] = ' ';

		replaceSpaces.replace(input);

		assertArrayEquals(new char[] {'p', 'e', 'd', 'r', 'o', '%', '2', '0'}, input);
	}

	@Test
	public void shouldReplaceSpacesWithTheStatementCharWithMoreThanOneSpace()
	{
		char[] input = new char[13];
		input[0] = 'p';
		input[1] = 'e';
		input[2] = ' ';
		input[3] = 'd';
		input[4] = 'r';
		input[5] = 'o';
		input[6] = ' ';
		input[7] = 'g';
		input[8] = 's';

		replaceSpaces.replace(input);

		assertArrayEquals(
			new char[] {'p', 'e', '%', '2', '0', 'd', 'r', 'o', '%', '2', '0', 'g', 's'}, input);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays2()
	{
		replaceSpaces.replace2(null);
	}

	@Test
	public void shouldReplaceAllTheContentIfTheArrayIsFullOfSpaces2()
	{
		char[] input = new char[6];
		input[0] = ' ';
		input[1] = ' ';

		replaceSpaces.replace2(input);

		assertArrayEquals(new char[] {'%', '2', '0', '%', '2', '0'}, input);
	}

	@Test
	public void shouldReplaceSpacesWithTheStatementCharWithJustOneSpace2()
	{
		char[] input = new char[8];
		input[0] = 'p';
		input[1] = 'e';
		input[2] = ' ';
		input[3] = 'd';
		input[4] = 'r';
		input[5] = 'o';

		replaceSpaces.replace2(input);

		assertArrayEquals(new char[] {'p', 'e', '%', '2', '0', 'd', 'r', 'o'}, input);
	}

	@Test
	public void shouldReplaceSpacesWithTheStatementCharWithJustOneSpaceAtTheEndOfTheContent2()
	{
		char[] input = new char[8];
		input[0] = 'p';
		input[1] = 'e';
		input[2] = 'd';
		input[3] = 'r';
		input[4] = 'o';
		input[5] = ' ';

		replaceSpaces.replace2(input);

		assertArrayEquals(new char[] {'p', 'e', 'd', 'r', 'o', '%', '2', '0'}, input);
	}

	@Test
	public void shouldReplaceSpacesWithTheStatementCharWithMoreThanOneSpace2()
	{
		char[] input = new char[13];
		input[0] = 'p';
		input[1] = 'e';
		input[2] = ' ';
		input[3] = 'd';
		input[4] = 'r';
		input[5] = 'o';
		input[6] = ' ';
		input[7] = 'g';
		input[8] = 's';

		replaceSpaces.replace2(input);

		assertArrayEquals(
			new char[] {'p', 'e', '%', '2', '0', 'd', 'r', 'o', '%', '2', '0', 'g', 's'}, input);
	}
}
