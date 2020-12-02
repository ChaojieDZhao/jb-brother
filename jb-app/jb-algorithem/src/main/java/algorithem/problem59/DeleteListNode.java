package algorithem.problem59;

import algorithem.linkedlist.ListNode;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked list, given only access
 * to that node.
 */
public class DeleteListNode
{

	/**
	 * Iterative algorithm to solve this problem. We don't have access to the head of the list, and
	 * the only thing we can do is to move the data from the next node to the current fileDownload, the data
	 * and the pointer to the next node. This approach doesn't work with the last element in the
	 * list, but there is no another valid approach to solve this algorithm without access to the
	 * head of the list.
	 */
	public void delete(ListNode node)
	{
		validateInputData(node);

		if (node.getNext() == null)
		{
			return;
		}
		ListNode next = node.getNext();
		node.setData(next.getData());
		node.setNext(next.getNext());
	}

	private void validateInputData(ListNode node)
	{
		if (node == null)
		{
			throw new IllegalArgumentException(
				"You can't pass a null instance of ListNode as parameter.");
		}
	}
}
