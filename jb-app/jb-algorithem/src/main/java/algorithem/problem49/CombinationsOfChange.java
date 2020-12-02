package algorithem.problem49;

import algorithem.problem47.ReturnChange;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of moneys and fileDownload amount to change, can you write a method to calculate every
 * possible return change using all possible combinations of moneys minimizing the number of moneys
 * to return?
 */
public class CombinationsOfChange
{

	private final ReturnChange returnChange;

	public CombinationsOfChange()
	{
		this.returnChange = new ReturnChange();
	}

	/**
	 * Combination of an iterative and recursive approach to resolve this problem. We are using
	 * ReturnChange problem to create this algorithm. The complexity order of this algorithm in space
	 * terms is equals to O(N) where N is the number of possible combinations. In time terms, the
	 * complexity order is equals to O(N*M) where N is the number of moneys in the array and M the
	 * complexity order of ReturnChange algorithm.
	 */
	public List<List<Integer>> get(int[] availableChange, int value)
	{
		validateInputData(availableChange, value);

		List<List<Integer>> changeCombinations = new LinkedList<List<Integer>>();
		for (int i = 0; i < availableChange.length; i++)
		{
			int[] availableMoneys = Arrays.copyOfRange(availableChange, 0, availableChange.length - i);
			List<Integer> change = getChange(availableMoneys, value);
			changeCombinations.add(change);
		}
		return changeCombinations;
	}

	private List<Integer> getChange(int[] availableMoneys, int value)
	{
		return returnChange.calculate(availableMoneys, value);
	}

	private void validateInputData(int[] availableChange, int value)
	{
		if (availableChange == null || value <= 0)
		{
			throw new IllegalArgumentException(
				"You can't pass a null array or a value minor or equals to zero as argument.");
		}
	}
}
