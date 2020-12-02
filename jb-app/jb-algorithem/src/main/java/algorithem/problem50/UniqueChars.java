package algorithem.problem50;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现一个方法去判断一个字符是否有重复的字符出现，如不需使用多余的数据结构。
 */
public class UniqueChars
{

	/**
	 * 迭代的角度解决该问题，时间和空间复杂度为O(N)，N为字符串长度。
	 */
	public boolean evaluate(String input)
	{
		validateInput(input);

		Set<Integer> charsCounter = new HashSet<Integer>();
		for (char c : input.toCharArray())
		{
			if (charsCounter.contains((int)c))
			{
				return false;
			}
			else
			{
				charsCounter.add((int)c);
			}
		}
		return true;
	}

	/**
	 * 这种快速解决方案基于一个重要的知识，字符集为ASCII并且ASCII的每个字符编码都不相同，使用了一个数组来代替上个方法的HashMap，该数组的长度就是ASCII字符的个数。
	 * 该方案的时间复杂度为O(N),但是空间复杂度为O(1)
	 */
	public boolean evaluate2(String input)
	{
		validateInput(input);

		int[] chars = new int[256];
		for (char c : input.toCharArray())
		{
			if (chars[c] >= 1)
			{
				return false;
			}
			else
			{
				chars[c]++;
			}
		}
		return true;
	}

	private void validateInput(String input)
	{
		if (input == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance as parameter.");
		}
	}
}
