package algorithem.problem44;

import algorithem.binarytree.BinaryNode;
import algorithem.problem15.BinaryTreeInOrder;

import java.util.List;

/**
 * Given a Binary Search Tree, can you write a method to obtain the nTH min element?
 */
public class FindMinNumberAtPosition
{

	private final BinaryTreeInOrder inOrder;

	public FindMinNumberAtPosition()
	{
		this.inOrder = new BinaryTreeInOrder();
	}

	/**
	 * This algorithm has a complexity order in space and time terms of O(N) where N is the number of
	 * elements in the tree. It's based on the property of BST. If you go through a BST using a
	 * in order tour you'll get an ordered list of elements.
	 */
	public Integer find(BinaryNode<Integer> root, int position)
	{
		validateInputData(root, position);
		List<BinaryNode<Integer>> orderedElements = getOrderedElements(root);
		validatePosition(position, orderedElements);
		return orderedElements.get(position - 1).getData();
	}

	private List<BinaryNode<Integer>> getOrderedElements(BinaryNode root)
	{
		return inOrder.getRecursive(root);
	}

	private void validatePosition(int position, List<BinaryNode<Integer>> orderedElements)
	{
		if (position >= orderedElements.size())
		{
			throw new IllegalArgumentException(
				"The number of elements in the tree is minor than the position requested.");
		}
	}

	private void validateInputData(BinaryNode<Integer> root, int position)
	{
		if (root == null || position < 0)
		{
			throw new IllegalArgumentException("You can't pass null binary trees or negative positions.");
		}
	}
}
