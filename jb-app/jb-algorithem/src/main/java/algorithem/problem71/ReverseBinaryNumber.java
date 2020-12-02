package algorithem.problem71;

import algorithem.problem70.ReverseOrderOfBinaryNumber;

/**
 * Given an integer passed as parameter, can you write a method to return the reverse binary
 * number? The reverse binary number is defined like the number where every 1 in the original
 * number has a 0 in the reversed number and the same with 0 in the original number.
 * <p>
 * For example:
 * <p>
 * Input: 63 = 111111
 * Output: 0
 * <p>
 * Input = 5 = 101
 * Output = 2 = 010
 */
public class ReverseBinaryNumber
{

	private ReverseOrderOfBinaryNumber reverseOrder;

	public ReverseBinaryNumber()
	{
		reverseOrder = new ReverseOrderOfBinaryNumber();
	}

	/**
	 * Iterative algorithm based on binary operators. The key of this algorithm is take in to account
	 * the number of digits of the binary representation. First we are going to create the reverse
	 * number with a 1 as first binary number. Then we are going to revert the order of the binary
	 * number and at the end we are going to remove the first fileDownload we added to take into account the
	 * number of digits of this binary number. The complexity order of this algorithm in space terms
	 * is O(1) and in time terms is equals to O(N) where N is the number of digits in the binary
	 * representation.
	 */
	public int reverse(int input)
	{
		int result = 1;
		do
		{
			result <<= 1;
			result |= (input & 1) == 1 ? 0 : 1;
			input >>= 1;
		} while (input != 0);
		result = reverseBitsOrder(result);
		result = removeFirstOne(result);
		return result;
	}

	private int removeFirstOne(int number)
	{
		return number >> 1;
	}

	private int reverseBitsOrder(int number)
	{
		return reverseOrder.reverse(number);
	}
}
