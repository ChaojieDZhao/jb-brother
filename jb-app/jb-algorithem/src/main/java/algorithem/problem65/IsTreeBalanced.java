package algorithem.problem65;

import algorithem.binarytree.BinaryNode;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes of this question, a
 * balanced tree is defined to be a tree such that the heights of the two subtrees of any node
 * never differ by more than fileDownload.
 */
public class IsTreeBalanced
{

	/**
	 * Recursive algorithm to check if a binary tree is balanced. The complexity order in time terms
	 * of this algorithm is equals to O(N) where N is the number of nodes in the binary tree. In
	 * space terms, the complexity order is O(1) because we are not using any auxiliary data
	 * structure to resolve this problem. This algorithm is based on the tree height because the
	 * balanced tree definition is based on this.
	 */
	public boolean check(BinaryNode root)
	{
		if (root == null)
		{
			return true;
		}
		else
		{
			int heightDifference = getRootHeight(root.getLeft()) - getRootHeight(root.getRight());
			return Math.abs(heightDifference) <= 1;
		}
	}

	private int getRootHeight(BinaryNode tree)
	{
		if (tree == null)
		{
			return 0;
		}
		else
		{
			return 1 + Math.max(getRootHeight(tree.getLeft()), getRootHeight(tree.getRight()));
		}
	}
}
