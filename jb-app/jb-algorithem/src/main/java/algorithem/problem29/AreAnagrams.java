package algorithem.problem29;

import java.util.Arrays;

/**
 * Given two Strings passed as parameter, can you write a method to return true if this words are
 * anagrams?
 * <p>
 * For example:
 * <p>
 * Input: ana,naa Output: true
 * Input: pedro,ana Output: false
 */
public class AreAnagrams
{

	/**
	 * Iterative algorithm to solve this problem. Two words are anagrams just if contains the same
	 * number of letters. Using this property, we are going to sort and compare the letters inside
	 * the array.  The complexity order of this algorithm is O(N*Log(N)) where N is the number of
	 * letters in the largest word. In space terms, the complexity order of this algorithm is O(N).
	 */
	public boolean check(String a, String b)
	{
		if (a == null || b == null)
		{
			throw new IllegalArgumentException("You can't use null Strings as input.");
		}
		if (a.length() != b.length())
		{
			return false;
		}
		char[] charsA = a.toCharArray();
		Arrays.sort(charsA);
		char[] charsB = b.toCharArray();
		Arrays.sort(charsB);
		return Arrays.equals(charsA, charsB);
	}

}
