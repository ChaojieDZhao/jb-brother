package algorithem.problem34;

import java.util.ArrayList;
import java.util.List;

/**
 * 给予一个String集合,需要返回只匹配给予表达式的字符。
 * 表达式支持：'.'表示任意非空字符
 */
public class DotRegularExpression
{

	/**
	 * 复杂度为O(N),N为该字符的长度。
	 */
	private static boolean matchDotRegularExpression(String word, String pattern)
	{
		if (Math.abs(word.length() - pattern.length()) > 1)
		{
			return false;
		}
		else if (word.isEmpty() && pattern.isEmpty())
		{
			return true;
		}
		else if (pattern.charAt(0) == '.')
		{
			return matchDotRegularExpression(word.substring(1), pattern.substring(1));
		}
		else
		{
			boolean partialMatch = word.charAt(0) == pattern.charAt(0);
			return partialMatch && matchDotRegularExpression(word.substring(1), pattern.substring(1));
		}
	}

	/**
	 * 迭代和递归的方式解决该问题，该算法的时间复杂度是O(N*M)，N是String数组的长度，M就是上面的方法复杂度。
	 * 该算法的空间复杂度是O(N),因为我们使用了新数组来保存返回集。
	 */
	public String[] evaluate(String[] words, String pattern)
	{
		if (words == null || pattern == null)
		{
			throw new IllegalArgumentException("You can't pass null objects as input.");
		}
		List<String> result = new ArrayList<String>();
		for (String word : words)
		{
			if (matchDotRegularExpression(word, pattern))
			{
				result.add(word);
			}
		}
		return result.toArray(new String[result.size()]);
	}
}
