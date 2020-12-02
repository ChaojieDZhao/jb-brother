package algorithem.problem65;

import algorithem.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsTreeBalancedTest
{

	private IsTreeBalanced isTreeBalanced;

	@Before
	public void setUp()
	{
		isTreeBalanced = new IsTreeBalanced();
	}

	@Test
	public void shouldReturnTrueIfTreeIsNull()
	{
		boolean isBalanced = isTreeBalanced.check(null);

		assertTrue(isBalanced);
	}

	@Test
	public void shouldReturnFalseIfTreeIsNotBalanced()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
		BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);
		root.setLeft(n1);
		root.setRight(n2);
		n1.setLeft(n3);
		n3.setLeft(n4);

		boolean isBalanced = isTreeBalanced.check(root);

		assertFalse(isBalanced);
	}

	@Test
	public void shouldReturnTrueIfTreeIsBalanced()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
		BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);
		root.setLeft(n1);
		root.setRight(n2);
		n1.setLeft(n3);
		n2.setRight(n4);

		boolean isBalanced = isTreeBalanced.check(root);

		assertTrue(isBalanced);
	}
}
