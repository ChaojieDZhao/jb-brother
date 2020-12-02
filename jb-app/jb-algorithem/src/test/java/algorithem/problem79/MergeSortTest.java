package algorithem.problem79;

import algorithem.sortingalgorithm.SortingAlgorithm;
import algorithem.sortingalgorithms.SortingAlgorithmTest;

public class MergeSortTest extends SortingAlgorithmTest
{

	@Override
	public SortingAlgorithm getSortingAlgorithm()
	{
		return new MergeSort();
	}
}
