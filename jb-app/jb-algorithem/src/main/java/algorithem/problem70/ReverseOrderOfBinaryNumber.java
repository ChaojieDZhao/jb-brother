package algorithem.problem70;

/**
 * Given an integer passed as parameter, can you write a method to return the number equivalent to
 * the binary number with the reversed order?
 * <p>
 * For example:
 * <p>
 * Input = 5 = 101
 * Output = 5 = 101
 * <p>
 * Input = 13 = 1101
 * Output = 11 = 1011
 */
public class ReverseOrderOfBinaryNumber
{

	/**
	 * Iterative algorithm to solve this problem. Shifting the input number until the value is equals
	 * to zero and applying a simple mask we are going to create the reverted order binary number
	 * using a constant complexity time in space terms O(1) and a linear complexity order in time
	 * terms O(N) where N is equals to the number of digits of the input parameter into a binary
	 * representation.
	 */
	public int reverse(int number)
	{
		int result = 0;
		while (number != 0)
		{
			result <<= 1;
			result |= number & 1;
			number >>= 1;
		}
		return result;
	}
}
