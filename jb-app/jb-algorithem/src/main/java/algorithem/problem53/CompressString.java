package algorithem.problem53;

/**
 * 实现一个方法使用重复字符串次数来压缩一个字符串。
 * 比如aabcccccaaa 表示为 a2blc5a3.
 * 如果被压缩的字符串长度等于原始字符串长度则返回原始字符串。
 * 比如aabbcc 表示为a2b2c2则返回aabbcc
 */
public class CompressString
{

	/**
	 * 递归的方案：该算法的时间和空间复杂度都为O(N),N是字符串长度，该方法使用了两个检测判断分别用来检测空字符和字符长度为一的字符。
	 * 该方案使用了两个指针变量分别指向当前字符和前一个字符，一个计数变量来存储字符的出现次数。
	 * 当检测到字符变化时。会将该字符的局部压缩结果写入到返回变量StringBuilder中。
	 */
	public String compress(String src)
	{
		validateInput(src);

		if (src.isEmpty() || src.length() == 1)
		{
			return src;
		}

		StringBuilder stringBuilder = new StringBuilder();
		int repeatedCharCounter = 1;
		char previousChar = src.charAt(0);
		for (int i = 1; i < src.length(); i++)
		{
			char currentChar = src.charAt(i);

			if (isCharRepeated(previousChar, currentChar))
			{
				repeatedCharCounter++;
			}
			else
			{
				addChar(stringBuilder, previousChar);
				if (repeatedCharCounter > 1)
				{
					stringBuilder.append(repeatedCharCounter);
					repeatedCharCounter = 1;
				}
				previousChar = currentChar;
			}
		}

		appendLastCharIfNeeded(stringBuilder, repeatedCharCounter, previousChar);

		return stringBuilder.toString();
	}

	/**
	 * 交互式循环：该方法的复杂度为O(N)
	 * 一个用来得到哪些字符串，一个用来得到字符串的出现次数。
	 * First while loop for finding repeat groups, and inner while loop is for finding same characters
	 */
	public String compressAlternativeApproach(String src)
	{
		validateInput(src);

		if (src.isEmpty() || src.length() == 1)
		{
			return src;
		}
		int index = 0;
		int count = 1;
		StringBuilder stringBuilder = new StringBuilder();
		while (index < src.length())
		{
			while (index < src.length() - 1)
			{
				if (src.charAt(index) == src.charAt(index + 1))
				{
					index++;
					count++;
				}
				else
				{
					stringBuilder.append(src.charAt(index));
					if (count > 1)
					{
						stringBuilder.append(count);
					}
					count = 1;
					index++;
					break;
				}
			}
			if (index == src.length() - 1)
			{
				if (src.charAt(index) != src.charAt(index - 1))
				{
					stringBuilder.append(src.charAt(index));
				}
				else
				{
					stringBuilder.append(src.charAt(index));
					if (count > 1)
					{
						stringBuilder.append(count);
					}
				}
				index++;
				break;
			}
		}
		return stringBuilder.toString();
	}

	private boolean isCharRepeated(char previousChar, char currentChar)
	{
		return currentChar == previousChar;
	}

	private void appendLastCharIfNeeded(StringBuilder stringBuilder, int repeatedCharCounter,
		char previousChar)
	{
		if (repeatedCharCounter > 1)
		{
			addChar(stringBuilder, previousChar);
			stringBuilder.append(repeatedCharCounter);
		}
	}

	/**
	 * 反向递归方案，复杂度与上一个方法相同。
	 */
	public String compressRecursive(String src)
	{
		validateInput(src);

		if (src.length() <= 1)
		{
			return src;
		}

		return compressRecursiveInner(src, new StringBuilder(), 1, src.charAt(0), 1);
	}

	private String compressRecursiveInner(String src, StringBuilder sb, int i, char previousChar,
		int charCounter)
	{
		boolean thereIsNoMoreWordToCompress = i == src.length();
		if (thereIsNoMoreWordToCompress)
		{
			addChar(sb, previousChar);
			addCharCounterIfNeeded(sb, charCounter);
			return sb.toString();
		}
		else
		{
			if (isCharRepeated(src.charAt(i), previousChar))
			{
				return compressRecursiveInner(src, sb, i + 1, previousChar, charCounter + 1);
			}
			else
			{
				addChar(sb, previousChar);
				addCharCounterIfNeeded(sb, charCounter);
				return compressRecursiveInner(src, sb, i + 1, src.charAt(i), 1);
			}
		}
	}

	private void addCharCounterIfNeeded(StringBuilder sb, int charCounter)
	{
		if (charCounter > 1)
		{
			sb.append(charCounter);
		}
	}

	private void addChar(StringBuilder sb, char previousChar)
	{
		sb.append(previousChar);
	}

	private void validateInput(String src)
	{
		if (src == null)
		{
			throw new IllegalArgumentException("You can't pass a null String as input parameter.");
		}
	}
}
