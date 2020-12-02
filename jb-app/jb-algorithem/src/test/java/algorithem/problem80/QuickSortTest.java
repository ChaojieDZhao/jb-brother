package algorithem.problem80;

import algorithem.sortingalgorithm.SortingAlgorithm;
import algorithem.sortingalgorithms.SortingAlgorithmTest;

public class QuickSortTest extends SortingAlgorithmTest
{

	@Override
	public SortingAlgorithm getSortingAlgorithm()
	{
		return new QuickSort();
	}
}
