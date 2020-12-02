package algorithem.problem78;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Before to read this test read AutoBoxingTrick class, this is a really funny Java trick.
 */
public class AutoBoxingTrickTest
{

	private AutoBoxingTrick autoBoxingTrick;

	@Before
	public void setUp()
	{
		autoBoxingTrick = new AutoBoxingTrick();
	}

	@Test
	public void shouldReturnFalseButReturnsTrue()
	{
		assertTrue(autoBoxingTrick.compare(1, 1));
	}

	@Test
	public void shouldReturnFalseAndReturnsFalse()
	{
		assertFalse(autoBoxingTrick.compare(1000, 1000));
	}
}
