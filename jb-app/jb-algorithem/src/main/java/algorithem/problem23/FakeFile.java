package algorithem.problem23;

/**
 * Class created to simulate a File object for problem 23.
 */
class FakeFile
{

	private final String[] lines;

	private int index;

	FakeFile(String... lines)
	{
		this.lines = lines != null ? lines : new String[0];
	}

	String getLine()
	{
		return index <= lines.length - 1 ? lines[index++] : null;
	}
}
