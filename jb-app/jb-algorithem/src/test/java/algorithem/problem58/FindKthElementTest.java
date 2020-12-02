package algorithem.problem58;

import algorithem.linkedlist.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindKthElementTest
{

	private FindKthElement findElement;

	@Before
	public void setUp()
	{
		findElement = new FindKthElement();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullListNodes()
	{
		findElement.find(null, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativePositions()
	{
		findElement.find(new ListNode<Integer>(3), -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldNotAcceptPositionsGreaterThanListSize()
	{
		findElement.find(new ListNode<Integer>(3), 2);
	}

	@Test
	public void shouldReturnLastNodeIfPositionIsZero()
	{
		ListNode result = findElement.find(new ListNode<Integer>(1), 0);

		ListNode<Integer> expectedNode = new ListNode<Integer>(1);
		assertEquals(expectedNode, result);
	}

	@Test
	public void shouldReturnFirstElementIfPositionIsEqualsToListSizeMinusOne()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode result = findElement.find(list, 2);

		ListNode<Integer> expectedNode = new ListNode<Integer>(1);
		assertEquals(expectedNode, result);
	}

	@Test
	public void shouldReturnKthToLastElement()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode result = findElement.find(list, 1);

		ListNode<Integer> expectedNode = new ListNode<Integer>(2);
		assertEquals(expectedNode, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullListNodes2()
	{
		findElement.find2(null, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativePositions2()
	{
		findElement.find2(new ListNode<Integer>(3), -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldNotAcceptPositionsGreaterThanListSize2()
	{
		findElement.find2(new ListNode<Integer>(3), 2);
	}

	@Test
	public void shouldReturnLastNodeIfPositionIsZero2()
	{
		ListNode result = findElement.find2(new ListNode<Integer>(1), 0);

		ListNode<Integer> expectedNode = new ListNode<Integer>(1);
		assertEquals(expectedNode, result);
	}

	@Test
	public void shouldReturnFirstElementIfPositionIsEqualsToListSizeMinusOne2()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode result = findElement.find2(list, 2);

		ListNode<Integer> expectedNode = new ListNode<Integer>(1);
		assertEquals(expectedNode, result);
	}

	@Test
	public void shouldReturnKthToLastElement2()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode result = findElement.find2(list, 1);

		ListNode<Integer> expectedNode = new ListNode<Integer>(2);
		assertEquals(expectedNode, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullListNodes3()
	{
		findElement.find3(null, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNegativePositions3()
	{
		findElement.find3(new ListNode<Integer>(3), -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldNotAcceptPositionsGreaterThanListSize3()
	{
		findElement.find3(new ListNode<Integer>(3), 2);
	}

	@Test
	public void shouldReturnLastNodeIfPositionIsZero3()
	{
		ListNode result = findElement.find3(new ListNode<Integer>(1), 0);

		ListNode<Integer> expectedNode = new ListNode<Integer>(1);
		assertEquals(expectedNode, result);
	}

	@Test
	public void shouldReturnFirstElementIfPositionIsEqualsToListSizeMinusOne3()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode result = findElement.find3(list, 2);

		ListNode<Integer> expectedNode = new ListNode<Integer>(1);
		assertEquals(expectedNode, result);
	}

	@Test
	public void shouldReturnKthToLastElement3()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode result = findElement.find3(list, 1);

		ListNode<Integer> expectedNode = new ListNode<Integer>(2);
		assertEquals(expectedNode, result);
	}

	private ListNode<Integer> createList(int[] integers)
	{
		ListNode<Integer> head = new ListNode<Integer>(integers[0]);
		if (integers.length > 1)
		{
			ListNode<Integer> prevNode = head;
			for (int i = 1; i < integers.length; i++)
			{
				ListNode<Integer> currentNode = new ListNode<Integer>(integers[i]);
				prevNode.setNext(currentNode);
				prevNode = currentNode;
			}
		}
		return head;
	}
}
