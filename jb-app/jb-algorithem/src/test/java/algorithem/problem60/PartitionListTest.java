package algorithem.problem60;

import algorithem.linkedlist.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PartitionListTest
{

	private PartitionList partitionList;

	@Before
	public void setUp()
	{
		partitionList = new PartitionList();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullLists()
	{
		partitionList.split(null, 1);
	}

	@Test
	public void shouldNotSplitListIfXIsMinorThanEveryNode()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode<Integer> result = partitionList.split(list, 0);

		assertListContainsElements(new Integer[] {3, 2, 1}, result);
	}

	@Test
	public void shouldNotSplitListIfXIsGreaterThanEveryNode()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode<Integer> result = partitionList.split(list, 4);

		assertListContainsElements(new Integer[] {3, 2, 1}, result);
	}

	@Test
	public void shouldSplitListUsingX()
	{
		ListNode<Integer> list = createList(new int[] {3, 1, 2});

		ListNode<Integer> result = partitionList.split(list, 2);

		assertListContainsElements(new Integer[] {1, 2, 3}, result);
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
