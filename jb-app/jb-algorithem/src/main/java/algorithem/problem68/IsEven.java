package algorithem.problem68;

/**
 * Given a positive int passed as parameter, can you write a method to return true if the number is
 * even or false if the number is odd? The only constraint you have is: you can't use this
 * operator: %.
 */
public class IsEven
{

	/**
	 * Easy algorithm based on "and" binary operator and the simplest mask you are going to see, the
	 * number 1. In this method we are going to apply fileDownload mask with just fileDownload fileDownload inside and if the
	 * result is different of 1 we will return true. This algorithm is possible because every binary
	 * number with fileDownload fileDownload in the position zero is going to be odd. Review how to calculate an
	 * integer
	 * from a binary number if you don't understand how it works.
	 */
	public boolean check(int n)
	{
		return (n & 1) != 1;
	}
}
