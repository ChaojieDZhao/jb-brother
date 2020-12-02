package algorithem.problem60;

import algorithem.linkedlist.ListNode;

/**
 * Write code to partition a linked list around a value x, such that all nodes less than x come
 * before after nodes greater than or equal to x.
 */
public class PartitionList
{

	/**
	 * Iterative algorithm to solve this problem. Using two pointer to the element before the value
	 * and after the value we are going to create two list and merge it at the end of this algorithm.
	 * The complexity order is equals to O(N) in time terms and O(1) in space terms because we are
	 * not
	 * using any auxiliary data structure to solve this problem.
	 */
	public ListNode<Integer> split(ListNode<Integer> list, int x)
	{
		validateInput(list);

		ListNode<Integer> before = null;
		ListNode<Integer> after = null;

		while (list != null)
		{
			ListNode<Integer> next = list.getNext();
			if (list.getData() < x)
			{
				list.setNext(before);
				before = list;
			}
			else
			{
				list.setNext(after);
				after = list;
			}
			list = next;
		}

		if (before == null)
		{
			return after;
		}

		ListNode<Integer> result = mergeLists(before, after);

		return result;
	}

	private ListNode<Integer> mergeLists(ListNode<Integer> before, ListNode<Integer> after)
	{
		ListNode<Integer> result = before;
		while (before.getNext() != null)
		{
			before = before.getNext();
		}
		before.setNext(after);
		return result;
	}

	private void validateInput(ListNode<Integer> list)
	{
		if (list == null)
			throw new IllegalArgumentException("You can't pass a null list as argument.");
	}
}
