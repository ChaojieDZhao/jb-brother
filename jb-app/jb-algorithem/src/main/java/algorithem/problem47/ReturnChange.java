package algorithem.problem47;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of available money and a value as input, can you write a method to return all
 * that change using the less money as possible.
 */
public class ReturnChange
{

	/**
	 * Iterative solution to this algorithm. This algorithm has a complexity order equals to O(N)
	 * where N is the billAmount/maxMoneyValue. At the end, linear to the value passed as parameter.
	 */
	public List<Integer> calculate(int[] moneys, int billAmount)
	{
		validateInputData(moneys, billAmount);

		List<Integer> change = new LinkedList<Integer>();
		while (billAmount > 0 && moneys.length > 0)
		{
			for (int i = moneys.length - 1; i >= 0; i--)
			{
				if (billAmount - moneys[i] >= 0)
				{
					change.add(moneys[i]);
					billAmount -= moneys[i];
					break;
				}
			}
		}
		return change;
	}

	private void validateInputData(int[] moneys, int billAmount)
	{
		if (moneys == null || moneys.length == 0 || billAmount <= 0)
		{
			throw new IllegalArgumentException(
				"Review your parameters used as input, you can't use a null or empty array of moneys or "
					+ "a bill amount equals or less than zero.");
		}
	}
}
