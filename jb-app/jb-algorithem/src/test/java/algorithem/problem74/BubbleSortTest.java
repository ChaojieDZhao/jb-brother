package algorithem.problem74;

import algorithem.sortingalgorithm.SortingAlgorithm;
import algorithem.sortingalgorithms.SortingAlgorithmTest;

public class BubbleSortTest extends SortingAlgorithmTest
{

	@Override
	public SortingAlgorithm getSortingAlgorithm()
	{
		return new BubbleSort();
	}
}
