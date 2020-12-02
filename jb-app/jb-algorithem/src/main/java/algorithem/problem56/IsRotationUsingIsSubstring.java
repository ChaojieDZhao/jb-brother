package algorithem.problem56;

/**
 * 假设已有一个isSubString方法可以检查一个字符串是否包含另一个字符串。
 * 有两个字符串参数，实现一个方法检测一个字符是否是另一个字符的循环体。
 * 只能调用一次isSubString方法。
 * 比如 waterbottle 就是bottlewater的一个循环体
 */
public class IsRotationUsingIsSubstring
{

	/**
	 * 就相当于一个脑筋急转弯，思路如下：
	 * s1 = waterbottle
	 * s2 = erbotlewat
	 * s1s1 = waterbottlewaterbottle
	 * return "waterbottlewaterbottle".contains(erbotlewat)
	 */
	public boolean check(String s1, String s2)
	{
		validateInput(s1, s2);

		if (haveSameLength(s1, s2))
		{
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}

	private boolean isSubstring(String s1s2, String s2)
	{
		return s1s2.contains(s2);
	}

	private void validateInput(String s1, String s2)
	{
		if (s1 == null || s2 == null)
		{
			throw new IllegalArgumentException("You can't pass null instances of s1 or s2 as parameter.");
		}
	}

	private boolean haveSameLength(String s1, String s2)
	{
		return s1.length() == s2.length();
	}
}
