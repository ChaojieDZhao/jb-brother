package algorithem.problem29;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AreAnagramsTest
{

	private AreAnagrams areAnagrams;

	@Before
	public void setUp()
	{
		areAnagrams = new AreAnagrams();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput()
	{
		areAnagrams.check(null, null);
	}

	@Test
	public void shouldReturnTrueIfBothWordsAreEmpty()
	{
		assertTrue(areAnagrams.check("", ""));
	}

	@Test
	public void shouldReturnFalseIfOneWordIsEmptyAndTheOtherNo()
	{
		assertFalse(areAnagrams.check("", "pedro"));
	}

	@Test
	public void shouldReturnFalseIfWordsAreNotAnagrams()
	{
		assertFalse(areAnagrams.check("ana", "pedro"));
	}

	@Test
	public void shouldReturnTrueIfWordsAreAnagrams()
	{
		assertTrue(areAnagrams.check("ana", "naa"));
	}

	@Test
	public void shouldReturnFalseEvenIfTheSumOfTheCharsIsEqual()
		throws Exception
	{
		assertFalse(areAnagrams.check("abc", "efg"));
	}
}
