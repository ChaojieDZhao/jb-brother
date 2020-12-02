package algorithem.problem66;

import algorithem.binarytree.BinaryNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at
 * each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
 */
public class TreeToListByLevel
{

	/**
	 * Iterative algorithm to resolve this problem. Binary tree traversal by level implemented with a
	 * complexity order in time and space terms equivalent to O(N).
	 */
	public List<BinaryNode> transform(BinaryNode tree)
	{
		if (tree == null)
		{
			return Collections.EMPTY_LIST;
		}

		List<BinaryNode> nodesByLevel = new LinkedList<BinaryNode>();
		Queue<BinaryNode> stack = new LinkedList<BinaryNode>();
		stack.add(tree);
		while (!stack.isEmpty())
		{
			BinaryNode node = stack.remove();
			nodesByLevel.add(node);
			if (node.getLeft() != null)
			{
				stack.add(node.getLeft());
			}
			if (node.getRight() != null)
			{
				stack.add(node.getRight());
			}
		}
		return nodesByLevel;
	}
}
