package algorithem.problem69;

/**
 * Given two integers passed as argument, can you write a function to determine the number of bits
 * required to convert integer A to integer B.
 */
public class BitsToTransform
{

	/**
	 * Iterative algorithm created to calculate a XOR using the input integers. Once you have the XOR
	 * you can iterate through the XOR to count the number of bits equals to 1. To iterate over the
	 * integer we are going to modify the value of the XOR integer using & operator with XOR & XOR
	 * -1.
	 * <p>
	 * For example:
	 * <p>
	 * numA = 001
	 * numB = 101
	 * XOR  = 100
	 * <p>
	 * Iteration 1 -> XOR = 100
	 * Iteration 2 -> XOR = 100 & 011 = 000 // Loop body not executed.
	 */
	public int calculate(int numA, int numB)
	{
		int differentBits = 0;
		for (int XOR = numA ^ numB; XOR != 0; XOR = XOR & (XOR - 1))
		{
			differentBits++;
		}
		return differentBits;
	}
}
