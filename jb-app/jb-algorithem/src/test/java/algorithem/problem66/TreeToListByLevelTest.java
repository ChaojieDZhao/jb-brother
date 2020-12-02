package algorithem.problem66;

import algorithem.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeToListByLevelTest
{

	private TreeToListByLevel toList;

	@Before
	public void setUp()
	{
		toList = new TreeToListByLevel();
	}

	@Test
	public void shouldReturnEmptyListIfTreeIsNull()
	{
		List<BinaryNode> result = toList.transform(null);

		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldReturnListWithJustOneNodeIfTreeContainsJustOneNode()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);

		List<BinaryNode> result = toList.transform(root);

		assertEquals(1, result.size());
		assertTrue(result.contains(root));
	}

	@Test
	public void shouldReturnListWithTreeElementsByLevel()
	{
		BinaryNode<Integer> root = new BinaryNode<Integer>(0);
		BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
		BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
		BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
		root.setLeft(n1);
		root.setRight(n2);
		n1.setLeft(n3);

		List<BinaryNode> result = toList.transform(root);

		assertEquals(4, result.size());
		assertEquals(root, result.get(0));
		assertEquals(n1, result.get(1));
		assertEquals(n2, result.get(2));
		assertEquals(n3, result.get(3));
	}
}
