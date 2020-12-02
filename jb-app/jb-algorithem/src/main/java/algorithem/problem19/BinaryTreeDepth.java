package algorithem.problem19;

import algorithem.binarytree.BinaryNode;

/**
 * Given a Binary Tree passed as argument, can you write a method to calculate the tree depth?
 */
public class BinaryTreeDepth
{

	/**
	 * Recursive solution for this problem. The complexity order of this algorithm in time terms is
	 * O(N) where N is the number of elements in the binary tree. In space terms, the complexity order
	 * of this algorithm is O(1) because we are not using any additional data structure related to the
	 * number of nodes in the tree to resolve this problem.
	 */
	public int get(BinaryNode root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("You can't pass a null BinaryNode as parameter");
		}
		return getInner(root);
	}

	private int getInner(BinaryNode root)
	{
		if (root == null)
		{
			return 0;
		}
		else
		{
			return 1 + Math.max(getInner(root.getLeft()), getInner(root.getRight()));
		}
	}
}
