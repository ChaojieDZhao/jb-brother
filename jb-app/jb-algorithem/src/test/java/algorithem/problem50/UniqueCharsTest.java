package algorithem.problem50;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UniqueCharsTest
{

	private UniqueChars uniqueChars;

	@Before
	public void setUp()
	{
		uniqueChars = new UniqueChars();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput()
	{
		uniqueChars.evaluate(null);
	}

	@Test
	public void shouldReturnFalseIfInputStringIsEmpty()
	{
		assertTrue(uniqueChars.evaluate(""));
	}

	@Test
	public void shouldReturnTrueIfInputStringContainsDuplicatedChars()
	{
		assertFalse(uniqueChars.evaluate("vicente"));
	}

	@Test
	public void shouldReturnFalseIfInputStringDoesNotContainDuplicatedChars()
	{
		assertTrue(uniqueChars.evaluate("pedro"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullStringsAsInput2()
	{
		uniqueChars.evaluate2(null);
	}

	@Test
	public void shouldReturnTrueIfInputStringIsEmpty2()
	{
		assertTrue(uniqueChars.evaluate2(""));
	}

	@Test
	public void shouldReturnFalseIfInputStringContainsDuplicatedChars2()
	{
		assertFalse(uniqueChars.evaluate2("vicente"));
	}

	@Test
	public void shouldReturnTrueIfInputStringDoesNotContainDuplicatedChars2()
	{
		assertTrue(uniqueChars.evaluate2("pedro"));
	}
}
