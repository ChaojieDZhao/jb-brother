package algorithem.problem57;

import algorithem.linkedlist.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write code to remove duplicates from an unsorted linked list. How would you solve this problem
 * if
 * a temporary buffer is not allowed?
 */
public class RemoveListDuplicatedElements
{

	/**
	 * Iterative algorithm to solve this problem. The complexity order of this algorithm in time and
	 * space terms is O(N) where N is equals to the number of elements in the linked list.
	 */
	public void remove(ListNode<Integer> head)
	{
		validateInput(head);

		Set<Integer> elements = new HashSet<Integer>();
		ListNode<Integer> currentNode = head;
		ListNode<Integer> prevNode = null;
		while (currentNode != null)
		{
			if (elements.contains(currentNode.getData()))
			{
				prevNode.setNext(currentNode.getNext());
			}
			else
			{
				elements.add(currentNode.getData());
			}
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}
	}

	/**
	 * Iterative algorithm to solve this problem without any auxiliary data structure. The complexity
	 * order of this algorithm in space terms is equals to O(1) but in time terms is equals to
	 * O(N^2).
	 */
	public void remove2(ListNode<Integer> head)
	{
		validateInput(head);

		ListNode<Integer> node = head;
		while (node != null)
		{
			removeNextNodesWithValue(node);
			node = node.getNext();
		}
	}

	private void validateInput(ListNode head)
	{
		if (head == null)
		{
			throw new IllegalArgumentException("You can't pass a null ListNode as instance.");
		}
	}

	private void removeNextNodesWithValue(ListNode<Integer> srcNode)
	{
		ListNode<Integer> prevNode = srcNode;
		ListNode<Integer> currentNode = srcNode.getNext();
		while (currentNode != null)
		{
			if (currentNode.getData().equals(srcNode.getData()))
			{
				prevNode.setNext(currentNode.getNext());
			}
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}
	}
}
