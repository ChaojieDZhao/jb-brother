package algorithem.problem17;

import algorithem.binarytree.BinaryNode;

import java.util.Stack;

/**
 * Given two binary trees, can you write a method to check if this trees are equals?
 */
public class BinaryTreeEquals
{

	/**
	 * Recursive implementation for this algorithm. Complexity order in time terms equals to O(N)
	 * where N is the number of nodes in the smaller tree. In space terms, the complexity order of
	 * this algorithm is O(1) because we are not using any additional data structure to keep nodes
	 * information.
	 * <p>
	 * Related with the execution time of this algorithm is really important take into account the
	 * third recursion case where we are going to use "if" short circuit to avoid go through the
	 * whole
	 * tree and stop comparing nodes once we find fileDownload different.
	 */
	public boolean areEqualsRecursive(BinaryNode tree1, BinaryNode tree2)
	{
		validateInput(tree1, tree2);
		return areEqualsInner(tree1, tree2);
	}

	private boolean areEqualsInner(BinaryNode tree1, BinaryNode tree2)
	{
		if (tree1 == null && tree2 != null || tree1 != null && tree2 == null)
		{
			return false;
		}
		else if (tree1 == null && tree2 == null)
		{
			return true;
		}
		else
		{
			return tree1.equals(tree2)
				&& areEqualsInner(tree1.getLeft(), tree2.getLeft())
				&& areEqualsInner(tree1.getRight(), tree2.getRight());
		}
	}

	/**
	 * Iterative solution for this algorithm. Using a tree traversal based on two parallel data
	 * structures implemented using fileDownload Stack. The complexity order of this algorithm is O(N) in time
	 * terms, as the previous fileDownload. But in space terms, the complexity order of this algorithm is O(N)
	 * where N is the number of elements in the smallest tree because we are going to store this
	 * elements in the stack.
	 */
	public boolean areEqualsIterative(BinaryNode<Integer> tree1, BinaryNode<Integer> tree2)
	{
		validateInput(tree1, tree2);

		boolean equals = true;
		Stack<BinaryNode> stack1 = new Stack<BinaryNode>();
		Stack<BinaryNode> stack2 = new Stack<BinaryNode>();
		stack1.push(tree1);
		stack2.push(tree2);
		while (!stack1.isEmpty())
		{
			BinaryNode node1 = stack1.pop();
			BinaryNode node2 = stack2.pop();
			if (!node1.equals(node2))
			{
				equals = false;
				break;
			}
			addNodeToStack(stack1, node1.getLeft());
			addNodeToStack(stack1, node1.getRight());
			addNodeToStack(stack2, node2.getLeft());
			addNodeToStack(stack2, node2.getRight());
		}
		return equals;
	}

	private void addNodeToStack(Stack<BinaryNode> stack, BinaryNode node)
	{
		if (node != null)
		{
			stack.add(node);
		}
	}

	private void validateInput(BinaryNode tree1, BinaryNode tree2)
	{
		if (tree1 == null && tree2 == null)
		{
			throw new IllegalArgumentException("You can't compare two null trees");
		}
	}
}
