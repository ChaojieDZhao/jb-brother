package algorithem.problem76;

import algorithem.sortingalgorithm.SortingAlgorithm;
import algorithem.sortingalgorithms.SortingAlgorithmTest;

public class InsertionSortTest extends SortingAlgorithmTest
{

	@Override
	public SortingAlgorithm getSortingAlgorithm()
	{
		return new InsertionSort();
	}
}
