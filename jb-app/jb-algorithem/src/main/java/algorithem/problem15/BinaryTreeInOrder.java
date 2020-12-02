package algorithem.problem15;

import algorithem.binarytree.BinaryNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//Consider the past, and you shall know the future

/**
 * Given a binary tree, can you write a method to get a List<BinaryNode> using a in order
 * traversal?
 */
public class BinaryTreeInOrder
{

	/**
	 * Recursive implementation of this binary tree traversal. The complexity order of this
	 * algorithms in time terms is O(N) and O(N) in space terms because we are using fileDownload additional
	 * data structure to return the result.
	 */
	public List<BinaryNode<Integer>> getRecursive(BinaryNode root)
	{
		validateBinaryNode(root);

		return getInner(root);
	}

	private List<BinaryNode<Integer>> getInner(BinaryNode<Integer> root)
	{
		List<BinaryNode<Integer>> result = new LinkedList<BinaryNode<Integer>>();
		if (root != null)
		{
			result.addAll(getInner(root.getLeft()));
			result.add(root);
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
	public List<BinaryNode<Integer>> getIterative(BinaryNode<Integer> root)
	{
		validateBinaryNode(root);

		List<BinaryNode<Integer>> result = new LinkedList<BinaryNode<Integer>>();
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		//Define a pointer to track nodes
		BinaryNode current = root;
		while (!stack.empty() || current != null)
		{
			if (current != null)
			{
				//If it is not null, push to stack and go down the tree to left
				stack.push(current);
				current = current.getLeft();
			}
			else
			{
				//If no left child pop stack, process the node then let current point to the right
				BinaryNode node = stack.pop();
				result.add(node);
				current = node.getRight();
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
