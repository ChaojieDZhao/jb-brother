package algorithem.problem14;

import algorithem.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryTreePreOrderTest
{

	private BinaryTreePreOrder binaryTreePreOrder;

	@Before
	public void setUp()
	{
		this.binaryTreePreOrder = new BinaryTreePreOrder();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullBinaryNodesRecursive()
	{
		binaryTreePreOrder.getRecursive(null);
	}

	@Test
	public void shouldReturnJustOneElementIfTheTreeContainsJustOneElementRecursive()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);

		List<BinaryNode> result = binaryTreePreOrder.getRecursive(root);

		List<BinaryNode<Integer>> expected = Arrays.asList(root);
		assertEquals(expected, result);
	}

	@Test
	public void shouldReturnBinaryNodesInPreOrderRecursive()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
		BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);

		root.setLeft(n1);
		root.setRight(n2);
		n1.setLeft(n3);
		n1.setRight(n4);

		List<BinaryNode> result = binaryTreePreOrder.getRecursive(root);

		List<BinaryNode<Integer>> expected = Arrays.asList(root, n1, n3, n4, n2);
		assertEquals(expected, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullBinaryNodesIterative()
	{
		binaryTreePreOrder.getIterative(null);
	}

	@Test
	public void shouldReturnJustOneElementIfTheTreeContainsJustOneElementIterative()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);

		List<BinaryNode> result = binaryTreePreOrder.getIterative(root);

		List<BinaryNode<Integer>> expected = Arrays.asList(root);
		assertEquals(expected, result);
	}

	@Test
	public void shouldReturnBinaryNodesInPreOrderIterative()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
		BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);

		root.setLeft(n1);
		root.setRight(n2);
		n1.setLeft(n3);
		n1.setRight(n4);

		List<BinaryNode> result = binaryTreePreOrder.getIterative(root);

		List<BinaryNode<Integer>> expected = Arrays.asList(root, n1, n3, n4, n2);
		assertEquals(expected, result);
	}
}
