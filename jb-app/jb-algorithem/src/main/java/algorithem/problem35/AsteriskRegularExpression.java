package algorithem.problem35;

import java.util.LinkedList;
import java.util.List;

/**
 * 给予两个字符串，实现一个方法返回一个字符是否匹配给定的表达式。
 * 表达式支持： '*'  表示前一個字符任意长度。
 */
public class AsteriskRegularExpression
{

	/**
	 * 使用递归方案，该方案复杂度接近O(N)。
	 */
	private static boolean matchAsteriskRegularExpression(String word, String pattern)
	{
		if (word.isEmpty() && pattern.isEmpty())
		{
			return true;
		}
		else if (word.isEmpty() || pattern.isEmpty())
		{
			return false;
		}
		else if (pattern.charAt(0) == '*')
		{
			boolean matchRestOfWord = matchAsteriskRegularExpression(word.substring(1), pattern);
			boolean matchRestOfPattern = matchAsteriskRegularExpression(word, pattern.substring(1));
			return matchRestOfWord || matchRestOfPattern;
		}
		else
		{
			boolean partialMatch = word.charAt(0) == pattern.charAt(0);
			return partialMatch && matchAsteriskRegularExpression(word.substring(1),
				pattern.substring(1));
		}
	}

	/**
	 * Combination of iterative and recursive approaches to resolve this problem. The complexity
	 * order in time terms is O(N*M) where N is the number of elements in the input array and M the
	 * size of the word in the array. In space terms, the complexity order of this algorithm is O(N)
	 * because we are using an additional data structure to store the result.
	 */
	public String[] evaluate(String[] words, String pattern)
	{
		if (words == null || pattern == null)
		{
			throw new IllegalArgumentException("You can't use null instances as input.");
		}

		List<String> result = new LinkedList<String>();
		for (String word : words)
		{
			if (matchAsteriskRegularExpression(word, pattern))
			{
				result.add(word);
			}
		}
		return result.toArray(new String[result.size()]);
	}

}
