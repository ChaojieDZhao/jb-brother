package algorithem.problem15;

import algorithem.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryTreeInOrderTest
{

	private BinaryTreeInOrder binaryTreeInOrder;

	@Before
	public void setUp()
	{
		this.binaryTreeInOrder = new BinaryTreeInOrder();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullBinaryNodesRecursive()
	{
		binaryTreeInOrder.getRecursive(null);
	}

	@Test
	public void shouldReturnJustOneElementIfTheTreeContainsJustOneElementRecursive()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);

		List<BinaryNode<Integer>> result = binaryTreeInOrder.getRecursive(root);

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

		List<BinaryNode<Integer>> result = binaryTreeInOrder.getRecursive(root);

		List<BinaryNode<Integer>> expected = Arrays.asList(n3, n1, n4, root, n2);
		assertEquals(expected, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullBinaryNodesIterative()
	{
		binaryTreeInOrder.getIterative(null);
	}

	@Test
	public void shouldReturnJustOneElementIfTheTreeContainsJustOneElementIterative()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);

		List<BinaryNode<Integer>> result = binaryTreeInOrder.getIterative(root);

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

		List<BinaryNode<Integer>> result = binaryTreeInOrder.getIterative(root);

		List<BinaryNode<Integer>> expected = Arrays.asList(n3, n1, n4, root, n2);
		assertEquals(expected, result);
	}
}
