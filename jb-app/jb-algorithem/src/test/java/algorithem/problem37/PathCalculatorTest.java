package algorithem.problem37;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathCalculatorTest
{

	private PathCalculator pathCalculator;

	@Before
	public void setUp()
	{
		pathCalculator = new PathCalculator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullPaths()
	{
		pathCalculator.calculate(null, null, null);
	}

	@Test
	public void shouldCalculatePath()
	{
		String finalPath = pathCalculator.calculate("/usr/bin/mail", "../../../etc/xyz", "../abc");

		assertEquals("/etc/abc/", finalPath);
	}
}
