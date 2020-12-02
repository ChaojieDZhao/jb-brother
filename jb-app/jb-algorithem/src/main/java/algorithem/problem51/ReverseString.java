package algorithem.problem51;

/**
 * 实现一个方法反转给定的字符串
 */
public class ReverseString
{

	/**
	 * 迭代方案，时间和空间的算法复杂度是O(N)，其中N是输入字符串中的字符数。
	 * 不过时间复杂度只是近似于O(N),因为String内部使用的是char数组表示字符，charAt(index)方法的复杂度为O(1).
	 */
	public String reverseIterative(String input)
	{
		validateInput(input);

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = input.length() - 1; i >= 0; i--)
		{
			stringBuilder.append(input.charAt(i));
		}
		return stringBuilder.toString();
	}

	/**
	 * 反向递归，优于第一种迭代方案。
	 */
	public String reverseRecursive(String input)
	{
		validateInput(input);
		return reverseRecursiveInner(input, input.length() - 1, new StringBuilder());
	}

	private String reverseRecursiveInner(String input, int i, StringBuilder stringBuilder)
	{
		if (i < 0)
		{
			return stringBuilder.toString();
		}
		else
		{
			stringBuilder.append(input.charAt(i--));
			return reverseRecursiveInner(input, i, stringBuilder);
		}
	}

	private void validateInput(String input)
	{
		if (input == null)
		{
			throw new IllegalArgumentException("You can't pass a null String as input parameter.");
		}
	}
}
