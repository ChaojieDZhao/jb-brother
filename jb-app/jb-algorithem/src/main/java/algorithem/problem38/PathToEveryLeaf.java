package algorithem.problem38;

import algorithem.binarytree.BinaryNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, can you write a method to calculate every path to every leaf in the tree.
 */
public class PathToEveryLeaf
{

	private static List<List<BinaryNode>> calculatePathToLeafs(BinaryNode<Integer> root,
		List<BinaryNode> path)
	{
		List<List<BinaryNode>> paths = new LinkedList<List<BinaryNode>>();
		if (root == null)
		{
			return paths;
		}
		else if (root.getLeft() == null && root.getRight() == null)
		{
			path.add(root);
			paths.add(path);
			return paths;
		}
		else
		{
			path.add(root);
			paths.addAll(calculatePathToLeafs(root.getLeft(), new LinkedList<BinaryNode>(path)));
			paths.addAll(calculatePathToLeafs(root.getRight(), new LinkedList<BinaryNode>(path)));
			return paths;
		}
	}

	/**
	 * Recursive algorithm to solve this problem. The complexity order of this algorithm is O(N)
	 * where N is the number of elements in the array in time and space terms.
	 */
	public List<List<BinaryNode>> calculate(BinaryNode<Integer> root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance of BinaryNode");
		}
		return calculatePathToLeafs(root, new LinkedList<BinaryNode>());
	}
}
