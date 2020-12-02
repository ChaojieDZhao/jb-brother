package algorithem.problem32;

/**
 * 给予两个字符串形参，实现一个方法判断第二个字符串是否包含第一个字符串。
 */
public class Contains
{

	/**
	 * 迭代的角度解决该问题，该方案的时间复杂度为O(N*M)，N是第一个单词的长度，M是第二个单词的长度。
	 * 空间复杂度是O(1)，因为没有使用任何的辅助数据类。
	 */
	public boolean evaluate(String w1, String w2)
	{
		if (w1 == null || w2 == null)
		{
			throw new IllegalArgumentException("You can't pass null strings as input.");
		}
		boolean contains = false;
		for (int i = 0; i < w2.length() - 1; i++)
		{
			if (w2.charAt(i) == w1.charAt(0))
			{
				for (int j = 0; j < w1.length(); j++)
				{
					if ((i + j) < w2.length() && w1.charAt(j) == w2.charAt(i + j) && j == w1.length() - 1)
					{
						contains = true;
						break;
					}
				}
			}
		}
		return contains;
	}
}
