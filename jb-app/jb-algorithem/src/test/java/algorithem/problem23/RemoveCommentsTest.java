package algorithem.problem23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveCommentsTest
{

	private RemoveComments removeComments;

	@Before
	public void setUp()
	{
		this.removeComments = new RemoveComments();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullFiles()
	{
		removeComments.remove(null);
	}

	@Test
	public void shouldReturnEmptyStringIfFileIsNull()
	{
		FakeFile file = new FakeFile();

		String result = removeComments.remove(file);

		assertEquals("", result);
	}

	@Test
	public void shouldReturnFileContentWithoutComments()
	{
		FakeFile file =
			new FakeFile("Hello world ", "this is the content /* of a fake file */", "of a real file");

		String result = removeComments.remove(file);

		assertEquals("Hello world this is the content of a real file", result);
	}

	@Test
	public void testForSlashAfterCommentOpen()
	{
		FakeFile file =
			new FakeFile("Hello world ", "this is the content /*/ of a fake file */", "of a real file");

		String result = removeComments.remove(file);

		assertEquals("Hello world this is the content of a real file", result);
	}

}
