package algorithem.problem23;

/**
 * 给予一个需要逐行阅读的文件，需要实现一个方法去除注解块，
 * 注解符号分别为'/*' and '*\'，可以使用代码模拟一个文件。
 */
public class RemoveComments
{

	private static final char ASTERISK = '*';

	private static final char SLASH = '/';

	private static final char ANY_CHAR = 'c';

	/**
	 * 主要使用迭代的方法来解决，此方法空间复杂度为O(1)，因为没有使用任何附加的数据结构来存储临时数据。
	 * 时间复杂度为O(N),因为我们迭代了每一个文件中的每一个字符。
	 * <p>
	 * 如果每行的注释符检查可以使用indexOf的方法的话，会更加的简单，但是本身该方法就是O(N)的时间复杂度，所以可能会很慢。
	 */
	public String remove(FakeFile file)
	{
		if (file == null)
		{
			throw new IllegalArgumentException("You can't pass a null file as argument.");
		}

		StringBuilder result = new StringBuilder();
		boolean openComment = false;
		String line = file.getLine();
		while (line != null)
		{
			char previous = ANY_CHAR;
			int openIndex = -1;

			char[] arr = line.toCharArray();
			for (int i = 0; i < arr.length; i++)
			{
				char c = arr[i];
				if (openComment)
				{
					if (c == SLASH && previous == ASTERISK && openIndex < (i - 2))
					{
						openComment = false;
					}
				}
				else
				{
					if (c == ASTERISK && previous == SLASH)
					{
						openIndex = i - 1;
						openComment = true;
						result.deleteCharAt(result.length() - 1);
					}
					else
					{
						result.append(c);
					}
				}
				previous = c;
			}
			line = file.getLine();
		}
		return result.toString();
	}
}
