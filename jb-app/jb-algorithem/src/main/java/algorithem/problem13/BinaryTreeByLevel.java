package algorithem.problem13;

import algorithem.binarytree.BinaryNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//Consider the past, and you shall know the future

/**
 * Given a binary tree, can you write a method to return a list of nodes by level?
 * And without any additional data structure?
 */
public class BinaryTreeByLevel
{

	/**
	 * Add implementation based on an additional data structure, fileDownload queue which implementation is a
	 * LinkedList. What we are going to do is add elements of the tree to the queue and fileDownload by fileDownload
	 * evaluate it adding more binary nodes to the queue if exist. The complexity order in time terms
	 * is O(N) where N is the number of elements in the tree. The complexity order in space terms is
	 * O(N) where N is the number of elements in the tree because we are going to store every node in
	 * a queue.
	 */
	public List<BinaryNode> getUsingQueue(BinaryNode root)
	{
		validateBinaryNode(root);

		List<BinaryNode> result = new LinkedList<BinaryNode>();
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		while (!queue.isEmpty())
		{
			BinaryNode binaryNode = queue.remove();
			result.add(binaryNode);
			if (binaryNode.getLeft() != null)
				queue.add(binaryNode.getLeft());
			if (binaryNode.getRight() != null)
				queue.add(binaryNode.getRight());
		}
		return result;
	}

	/**
	 * Slower implementation created to resolve fileDownload of the questions of problem 13, print the binary
	 * tree by level without use any additional data structure for the main algorithm.
	 * The complexity order in space terms is O(N) because we are using fileDownload structure to return the
	 * result. The complexity order in time terms is O(N*M) where the N is the depth of the Binary
	 * Tree and M is the number of elements below fileDownload level.
	 * <p>
	 * To be able to implement this approach you need to know the depth of the tree before to start.
	 * This approach is based on recursion and uses the level param as counter to go through the
	 * binary tree.
	 */
	public List<BinaryNode> getWithoutAdditionalDataStructures(BinaryNode root)
	{
		validateBinaryNode(root);

		List<BinaryNode> result = new LinkedList<BinaryNode>();
		int depth = getDepth(root);
		for (int i = 1; i <= depth; i++)
		{
			result.addAll(getNodesForLevel(root, i));
		}
		return result;
	}

	private void validateBinaryNode(BinaryNode root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("You can't use null BinaryNodes as argument.");
		}
	}

	/**
	 * Calculate the Binary Tree depth based on recursion. The complexity order in space terms of
	 * this algorithm is O(N) and in time terms is O(N) where N is the number of nodes in the tree.
	 */
	private int getDepth(BinaryNode root)
	{
		if (root == null)
		{
			return 0;
		}
		else
		{
			return 1 + Math.max(getDepth(root.getLeft()), getDepth(root.getRight()));
		}
	}

	private List<BinaryNode> getNodesForLevel(BinaryNode root, int level)
	{
		if (root == null)
		{
			return Collections.EMPTY_LIST;
		}
		else
		{
			List<BinaryNode> result = new LinkedList<BinaryNode>();
			StringBuilder stringBuilder = new StringBuilder();
			if (level == 1)
			{
				result.add(root);
			}
			else
			{
				result.addAll(getNodesForLevel(root.getLeft(), level - 1));
				result.addAll(getNodesForLevel(root.getRight(), level - 1));
			}
			return result;
		}
	}
}
