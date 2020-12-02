package algorithem.problem27;

/**
 * 反转一个句子的单词顺序。
 * 比如：
 * 输入： "Pedro Vicente Gómez"   输出："Gómez Vicente Pedro"
 */
public class ReverseSentence
{

	private static final String WORD_SEPARATOR = " ";

	/**
	 * 迭代的角度来解决问题，时间和空间复杂度均为0(N)，N代表该字符串中有N的单词。
	 */
	public String reverse(String sentence)
	{
		if (sentence == null)
		{
			throw new IllegalArgumentException("Input param can't be null.");
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] words = sentence.split(WORD_SEPARATOR);
		for (int i = words.length - 1; i >= 0; i--)
		{
			stringBuilder.append(words[i]);
			if (i != 0)
			{
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}
}
