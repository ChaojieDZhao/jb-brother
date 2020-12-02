package algorithem.problem36;

import algorithem.binarytree.BinaryNode;
import algorithem.problem15.BinaryTreeInOrder;

import java.util.List;

/**
 * Given an AVL (self balanced binary search tree), find the median. Remember you can't just take
 * the root element even if the tree is balanced because even with the tree balanced you don't know
 * if the median is exactly the root element on a left or right son.
 */
public class AvlTreeMedian
{

	BinaryTreeInOrder binaryTreeInOrder;

	public AvlTreeMedian()
	{
		this.binaryTreeInOrder = new BinaryTreeInOrder();
	}

	/**
	 * Iterative algorithm used to find the median of an AVL. This algorithm is based on a property
	 * of every AVL tree, you can get a sorted collection containing the elements of this tree using
	 * an in order traversal. Using this property we can get a sorted collection of nodes and then
	 * find the median. The complexity order of this algorithm is O(N) in time and space terms where
	 * N
	 * is the number of nodes in the tree.
	 */
	public double find(BinaryNode<Integer> root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("You can't pass a null binary tree to this method.");
		}

		List<BinaryNode<Integer>> sortedElements = binaryTreeInOrder.getIterative(root);
		double median = 0;
		if (sortedElements.size() % 2 == 0)
		{
			median = (sortedElements.get(sortedElements.size() / 2).getData() + sortedElements.get(
				sortedElements.size() / 2 - 1).getData()) / 2;
		}
		else
		{
			median = sortedElements.get(sortedElements.size() / 2).getData();
		}
		return median;
	}
}
