package algorithem.problem75;

import algorithem.sortingalgorithm.SortingAlgorithm;
import algorithem.sortingalgorithms.SortingAlgorithmTest;

public class SelectionSortTest extends SortingAlgorithmTest
{

	@Override
	public SortingAlgorithm getSortingAlgorithm()
	{
		return new SelectionSort();
	}
}
