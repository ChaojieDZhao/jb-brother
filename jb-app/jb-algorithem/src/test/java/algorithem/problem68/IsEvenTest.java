package algorithem.problem68;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsEvenTest
{

	private IsEven isEven;

	@Before
	public void setUp()
	{
		isEven = new IsEven();
	}

	@Test
	public void shouldReturnTrueIfNumberIsZero()
	{
		assertTrue(isEven.check(0));
	}

	@Test
	public void shouldReturnTrueIfNumberIsEven()
	{
		assertTrue(isEven.check(68));
	}

	@Test
	public void shouldReturnFalseIfNumberIsOdd()
	{
		assertFalse(isEven.check(99));
	}
}
