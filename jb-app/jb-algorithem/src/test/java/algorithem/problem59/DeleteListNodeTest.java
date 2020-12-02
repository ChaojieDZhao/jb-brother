package algorithem.problem59;

import algorithem.linkedlist.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeleteListNodeTest
{

	private DeleteListNode deleteNode;

	@Before
	public void setUp()
	{
		deleteNode = new DeleteListNode();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullNodes()
	{
		deleteNode.delete(null);
	}

	@Test
	public void shouldNotModifyNodeIfListContainsJustThatNode()
	{
		ListNode<Integer> head = new ListNode<Integer>(1);

		deleteNode.delete(head);

		assertEquals(head, head);
	}

	@Test
	public void shouldRemoveListNode()
	{
		ListNode<Integer> head = new ListNode<Integer>(0);
		ListNode<Integer> n1 = new ListNode<Integer>(1);
		ListNode<Integer> n2 = new ListNode<Integer>(2);
		head.setNext(n1);
		n1.setNext(n2);

		deleteNode.delete(n1);

		assertListContainsElements(new Integer[] {0, 2}, head);
	}

	@Test
	public void shouldRemoveHead()
	{
		ListNode<Integer> head = new ListNode<Integer>(0);
		ListNode<Integer> n1 = new ListNode<Integer>(1);
		ListNode<Integer> n2 = new ListNode<Integer>(2);
		head.setNext(n1);
		n1.setNext(n2);

		deleteNode.delete(head);

		assertListContainsElements(new Integer[] {1, 2}, n1);
	}

	private void assertListContainsElements(Integer[] integers, ListNode<Integer> head)
	{
		int arrayLength = integers.length;
		ListNode<Integer> currentNode = head;

		int i = 0;
		while (i < arrayLength)
		{
			assertEquals(integers[i], currentNode.getData());
			currentNode = currentNode.getNext();
			i++;
		}
		assertNull(currentNode);
	}
}
