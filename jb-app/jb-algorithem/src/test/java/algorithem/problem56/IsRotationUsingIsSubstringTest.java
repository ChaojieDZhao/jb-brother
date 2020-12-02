package algorithem.problem56;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsRotationUsingIsSubstringTest
{

	private IsRotationUsingIsSubstring isRotation;

	@Before
	public void setUp()
	{
		isRotation = new IsRotationUsingIsSubstring();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstancesAsFirstParameter()
	{
		isRotation.check(null, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstancesAsSecondParameter()
	{
		isRotation.check("", null);
	}

	@Test
	public void shouldReturnFalseIfS2IsNotRotationOfS1()
	{
		String s1 = "waterbottle";
		String s2 = "pedro";

		boolean result = isRotation.check(s1, s2);

		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueIfS2IsNotRotationOfS1()
	{
		String s1 = "waterbottle";
		String s2 = "erbottlewat";

		boolean result = isRotation.check(s1, s2);

		assertTrue(result);
	}
}
