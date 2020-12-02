package algorithem.problem46;

import algorithem.binarytree.BinaryNode;

/**
 * Given a binary tree as input param, can you write two methods to serialize and deserialize this
 * tree?
 */
public class BinaryTreeSerialization
{

	static int index = 0;

	public BinaryTreeSerialization()
	{
		index = 0;
	}

	/**
	 * Recursive algorithm based on a pre order traversal of the tree to generate an string with the
	 * content of the tree and # for every leaf. The complexity order of this algorithm is O(N) where
	 * N is the number of nodes in the tree.
	 */
	public String serialize(BinaryNode root)
	{
		validateInputTree(root);
		return serializeInner(root);
	}

	private String serializeInner(BinaryNode root)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if (root == null)
		{
			stringBuilder.append("#");
		}
		else
		{
			stringBuilder.append(root.getData());
			stringBuilder.append(serializeInner(root.getLeft()));
			stringBuilder.append(serializeInner(root.getRight()));
		}
		return stringBuilder.toString();
	}

	/**
	 * Recursive algorithm based a static index to recreate the original tree properly. The
	 * complexity order of this algorithm is O(N) where N is the number of chars in the input String.
	 */
	public BinaryNode<Integer> deserialize(String tree)
	{
		validateInput(tree);

		if (index >= tree.length())
		{
			return null;
		}
		else
		{
			char currentChar = tree.charAt(index);
			if (currentChar == '#')
			{
				index++;
				return null;
			}
			else
			{
				BinaryNode<Integer> node = new BinaryNode<Integer>(currentChar - 48);
				index++;
				node.setLeft(deserialize(tree));
				node.setRight(deserialize(tree));
				return node;
			}
		}
	}

	private void validateInputTree(BinaryNode root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance of tree to serialize.");
		}
	}

	private void validateInput(String tree)
	{
		if (tree == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance of tree to deserialize.");
		}
	}
}
