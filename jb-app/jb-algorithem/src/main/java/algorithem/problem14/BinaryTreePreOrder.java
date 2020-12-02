package algorithem.problem14;

import algorithem.binarytree.BinaryNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//Consider the past, and you shall know the future

/**
 * Given a binary tree, can you write a method to get a List<BinaryNode> using a pre order
 * traversal?
 */
public class BinaryTreePreOrder
{

	/**
	 * Recursive implementation of this binary tree traversal. The complexity order of this
	 * algorithms in time terms is O(N) and O(N) in space terms because we are using fileDownload additional
	 * data structure to return the result.
	 */
	public List<BinaryNode> getRecursive(BinaryNode root)
	{
		validateBinaryNode(root);
		return getInner(root);
	}

	private List<BinaryNode> getInner(BinaryNode root)
	{
		List<BinaryNode> result = new LinkedList<BinaryNode>();
		if (root != null)
		{
			result.add(root);
			result.addAll(getInner(root.getLeft()));
			result.addAll(getInner(root.getRight()));
		}
		return result;
	}

	/**
	 * Iterative implementation of this binary tree traversal. The complexity order in time terms of
	 * this algorithm is O(N) where N is the number of nodes in the tree. In space terms the
	 * complexity order of this algorithm is also O(N) where N is the number of nodes we have to
	 * store in the auxiliary data structure, the stack.
	 */
	public List<BinaryNode> getIterative(BinaryNode root)
	{
		validateBinaryNode(root);
		List<BinaryNode> result = new LinkedList<BinaryNode>();
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		stack.push(root);

		while (!stack.isEmpty())
		{
			BinaryNode node = stack.pop();
			result.add(node);
			if (node.hasRight())
			{
				stack.add(node.getRight());
			}
			if (node.hasLeft())
			{
				stack.add(node.getLeft());
			}
		}

		return result;
	}

	private void validateBinaryNode(BinaryNode root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("You can't pass a null BinaryNode.");
		}
	}
}
