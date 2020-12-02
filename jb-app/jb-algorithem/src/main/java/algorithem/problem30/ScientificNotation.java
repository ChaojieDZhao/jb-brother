package algorithem.problem30;

/**
 * Given an String containing a number expressed using scientific notation, can you write a method
 * to return the value of that number?
 */
public class ScientificNotation
{

	/**
	 * Iterative solution to this problem. The complexity order of this algorithm is O(N) because we
	 * have to use split and is the most expensive operation. In space terms the complexity order is
	 * O(N) and related directly to the size of the number in scientific notation.
	 */
	public long calculate(String number)
	{
		if (number == null || !number.contains("E"))
		{
			throw new IllegalArgumentException("Invalid format for number " + number);
		}
		String[] splittedNumber = splitScientificNumber(number);
		double first = getLeftPart(splittedNumber[0]);
		int sign = getNumberSign(splittedNumber[1]);
		int pow = getPow(splittedNumber[1]);
		Double result = sign * first * Math.pow(10, pow);
		return result.longValue();
	}

	private int getPow(String s)
	{
		return Integer.parseInt(s.substring(1, s.length()));
	}

	private int getNumberSign(String s)
	{
		return s.charAt(0) == '+' ? 1 : -1;
	}

	private double getLeftPart(String s)
	{
		return Double.parseDouble(s);
	}

	private String[] splitScientificNumber(String number)
	{
		return number.split("E");
	}
}
