package algorithem.problem53;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompressStringTest
{

	private CompressString compressString;

	@Before
	public void setUp()
	{
		compressString = new CompressString();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput()
	{
		compressString.compress(null);
	}

	@Test
	public void shouldNotCompressEmptyWords()
	{
		String wordToCompress = "";

		String result = compressString.compress(wordToCompress);

		assertEquals("", result);
	}

	@Test
	public void shouldNotCompressWordsWithJustOneChar()
	{
		String wordToCompress = "a";

		String result = compressString.compress(wordToCompress);

		assertEquals("a", result);
	}

	@Test
	public void shouldCompressWordsFullOfTheSameChars()
	{
		String wordToCompress = "aaa";

		String result = compressString.compress(wordToCompress);

		assertEquals("a3", result);
	}

	@Test
	public void shouldCompressWords()
	{
		String wordToCompress = "aabcccccaaa";

		String result = compressString.compress(wordToCompress);

		assertEquals("a2bc5a3", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInputAlternative()
	{
		compressString.compressAlternativeApproach(null);
	}

	@Test
	public void shouldNotCompressEmptyWordsAlternative()
	{
		String wordToCompress = "";

		String result = compressString.compressAlternativeApproach(wordToCompress);

		assertEquals("", result);
	}

	@Test
	public void shouldNotCompressWordsWithJustOneCharAlternative()
	{
		String wordToCompress = "a";

		String result = compressString.compressAlternativeApproach(wordToCompress);

		assertEquals("a", result);
	}

	@Test
	public void shouldCompressWordsFullOfTheSameCharsAlternative()
	{
		String wordToCompress = "aaa";

		String result = compressString.compressAlternativeApproach(wordToCompress);

		assertEquals("a3", result);
	}

	@Test
	public void shouldCompressWordsAlternative()
	{
		String wordToCompress = "aabcccccaaa";

		String result = compressString.compressAlternativeApproach(wordToCompress);

		assertEquals("a2bc5a3", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInputRecursive()
	{
		compressString.compressRecursive(null);
	}

	@Test
	public void shouldNotCompressEmptyWordsRecursive()
	{
		String wordToCompress = "";

		String result = compressString.compressRecursive(wordToCompress);

		assertEquals("", result);
	}

	@Test
	public void shouldNotCompressWordsWithJustOneCharRecursive()
	{
		String wordToCompress = "a";

		String result = compressString.compressRecursive(wordToCompress);

		assertEquals("a", result);
	}

	@Test
	public void shouldCompressWordsFullOfTheSameCharsRecursive()
	{
		String wordToCompress = "aaa";

		String result = compressString.compressRecursive(wordToCompress);

		assertEquals("a3", result);
	}

	@Test
	public void shouldCompressWordsRecursive()
	{
		String wordToCompress = "aabcccccaaa";

		String result = compressString.compressRecursive(wordToCompress);

		assertEquals("a2bc5a3", result);
	}
}
