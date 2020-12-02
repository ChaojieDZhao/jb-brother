package algorithem.problem22;

import algorithem.linkedlist.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseLinkedListTest
{

	private ReverseLinkedList reverseLinkedList;

	@Before
	public void setUp()
	{
		reverseLinkedList = new ReverseLinkedList();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullElementsIterative()
	{
		reverseLinkedList.reverseIterative(null);
	}

	@Test
	public void shouldAcceptLinkedListWithJustOneElementIterative()
	{
		ListNode<Integer> head = new ListNode<Integer>(1);

		assertEquals(head, reverseLinkedList.reverseIterative(head));
	}

	@Test
	public void shouldReverseLinkedListIterative()
	{
		ListNode<Integer> head = new ListNode<Integer>(1);
		ListNode<Integer> n2 = new ListNode<Integer>(2);
		ListNode<Integer> n3 = new ListNode<Integer>(3);
		ListNode<Integer> n4 = new ListNode<Integer>(4);
		head.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);

		ListNode reversedList = reverseLinkedList.reverseIterative(head);

		assertEquals(n4, reversedList);
		assertEquals(n3, reversedList.getNext());
		assertEquals(n2, reversedList.getNext().getNext());
		assertEquals(head, reversedList.getNext().getNext().getNext());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullElementsRecursive()
	{
		reverseLinkedList.reverseRecursive(null);
	}

	@Test
	public void shouldAcceptLinkedListWithJustOneElementRecursive()
	{
		ListNode<Integer> head = new ListNode<Integer>(1);

		assertEquals(head, reverseLinkedList.reverseRecursive(head));
	}

	@Test
	public void shouldReverseLinkedListRecursive()
	{
		ListNode<Integer> head = new ListNode<Integer>(1);
		ListNode<Integer> n2 = new ListNode<Integer>(2);
		ListNode<Integer> n3 = new ListNode<Integer>(3);
		ListNode<Integer> n4 = new ListNode<Integer>(4);
		head.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);

		ListNode reversedList = reverseLinkedList.reverseRecursive(head);

		assertEquals(n4, reversedList);
		assertEquals(n3, reversedList.getNext());
		assertEquals(n2, reversedList.getNext().getNext());
		assertEquals(head, reversedList.getNext().getNext().getNext());
	}
}
