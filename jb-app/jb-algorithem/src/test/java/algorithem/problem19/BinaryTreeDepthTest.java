package algorithem.problem19;

import algorithem.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeDepthTest
{

	private BinaryTreeDepth binaryTreeDepth;

	@Before
	public void setUp()
	{
		binaryTreeDepth = new BinaryTreeDepth();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullBinaryNodes()
	{
		binaryTreeDepth.get(null);
	}

	@Test
	public void shouldReturn1IfTreeContainsJustOneElement()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);

		int depth = binaryTreeDepth.get(root);

		assertEquals(1, depth);
	}

	@Test
	public void shouldCalculateBinaryTreeDepthEvenIfTreeContainsJustOneBigLeftBranch()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		root.setLeft(n1);
		n1.setLeft(n2);

		int depth = binaryTreeDepth.get(root);

		assertEquals(3, depth);
	}

	@Test
	public void shouldCalculateBinaryTreeDepthEvenIfTreeContainsJustOneBigRightBranch()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		root.setRight(n1);
		n1.setRight(n2);

		int depth = binaryTreeDepth.get(root);

		assertEquals(3, depth);
	}

	@Test
	public void shouldCalculateBinaryTreeDepthWithANormalBinaryTree()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
		BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);
		BinaryNode<Integer> n5 = new BinaryNode<Integer>(5);
		root.setLeft(n1);
		root.setRight(n2);
		n1.setLeft(n3);
		n1.setRight(n4);
		n4.setLeft(n5);

		int depth = binaryTreeDepth.get(root);

		assertEquals(4, depth);
	}
}
