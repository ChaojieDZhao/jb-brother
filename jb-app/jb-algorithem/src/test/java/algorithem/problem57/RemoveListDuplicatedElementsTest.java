package algorithem.problem57;

import algorithem.linkedlist.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RemoveListDuplicatedElementsTest
{

	private RemoveListDuplicatedElements removeDuplicates;

	@Before
	public void setUp()
	{
		removeDuplicates = new RemoveListDuplicatedElements();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstancesAsParameter()
	{
		removeDuplicates.remove(null);
	}

	@Test
	public void shouldNotModifyListIfThereAreNotDuplicatedElements()
	{
		ListNode<Integer> head = createList(new Integer[] {0, 1, 2, 3, 4});

		removeDuplicates.remove(head);

		assertListContainsElements(new Integer[] {0, 1, 2, 3, 4}, head);
	}

	@Test
	public void shouldRemoveDuplicatedElements()
	{
		ListNode<Integer> head = createList(new Integer[] {0, 1, 0, 2, 2, 3, 4, 4});

		removeDuplicates.remove(head);

		assertListContainsElements(new Integer[] {0, 1, 2, 3, 4}, head);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullInstancesAsParameter2()
	{
		removeDuplicates.remove2(null);
	}

	@Test
	public void shouldNotModifyListIfThereAreNotDuplicatedElements2()
	{
		ListNode<Integer> head = createList(new Integer[] {0, 1, 2, 3, 4});

		removeDuplicates.remove2(head);

		assertListContainsElements(new Integer[] {0, 1, 2, 3, 4}, head);
	}

	@Test
	public void shouldRemoveDuplicatedElements2()
	{
		ListNode<Integer> head = createList(new Integer[] {0, 1, 0, 2, 2, 3, 4, 4});

		removeDuplicates.remove2(head);

		assertListContainsElements(new Integer[] {0, 1, 2, 3, 4}, head);
	}

	private ListNode<Integer> createList(Integer[] integers)
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
