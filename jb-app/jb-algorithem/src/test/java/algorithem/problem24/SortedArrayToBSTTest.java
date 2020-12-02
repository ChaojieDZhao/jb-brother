package algorithem.problem24;

import algorithem.binarytree.BinaryNode;
import algorithem.problem15.BinaryTreeInOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SortedArrayToBSTTest
{

	private SortedArrayToBST sortedArrayToBST;

	@Before
	public void setUp()
	{
		sortedArrayToBST = new SortedArrayToBST();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArrays()
	{
		sortedArrayToBST.transform(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptAnEmptyArray()
	{
		Integer[] emptyArray = new Integer[0];
		sortedArrayToBST.transform(emptyArray);
	}

	@Test
	public void shouldReturnJustOneNodeIfTheArrayContainsJustOneElement()
	{
		Integer[] array = {1};

		BinaryNode<Integer> result = sortedArrayToBST.transform(array);

		assertEquals(new Integer(1), result.getData());
	}

	/**
	 * If you get an in order traversal of a BST you get a sorted collection of elements. We are
	 * going to use this property to assert the result.
	 */
	@Test
	public void shouldReturnOneBinarySearchTree()
	{
		Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8};

		BinaryNode<Integer> result = sortedArrayToBST.transform(array);

		BinaryTreeInOrder inOrder = new BinaryTreeInOrder();
		List<BinaryNode<Integer>> resultList = inOrder.getIterative(result);
		Integer[] resultArray = new Integer[resultList.size()];
		for (int i = 0; i < resultList.size(); i++)
		{
			resultArray[i] = resultList.get(i).getData();
		}
		assertArrayEquals(array, resultArray);
	}
}
