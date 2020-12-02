package algorithem.problem25;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class AnagramsTest
{

	private Anagrams anagrams;

	@Before
	public void setUp()
	{
		this.anagrams = new Anagrams();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStrings()
	{
		anagrams.get(null);
	}

	@Test
	public void shouldReturnAnEmptyListIfInputStringIsEmpty()
	{
		assertTrue(anagrams.get("").isEmpty());
	}

	@Test
	public void shouldCalculateEveryAnagram()
	{
		String input = "abc";

		List<String> result = anagrams.get(input);
		String[] arrayResult = new String[result.size()];
		result.toArray(arrayResult);

		String[] expectedArray = {"abc", "acb", "bac", "bca", "cab", "cba"};
		assertArrayEquals(expectedArray, arrayResult);
	}
}
