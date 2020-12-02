package algorithem.problem64;

import algorithem.linkedlist.ListNode;

/**
 * Given a singly linked list, can you write a method to return the element in the middle of the
 * list?
 */
public class GetTheElementInTheMiddleOfTheList
{

	/**
	 * Iterative solution to this problem. The complexity order of this algorithm in time terms is
	 * equals to O(N) where N is the number of elements in the list. In space terms, the complexity
	 * order is O(1) because we are not using any auxiliary data structure to solve this problem.
	 * This algorithm is based on know the size of the list, but this part of the algorithm is a
	 * problem for the execution time.
	 */
	public ListNode<Integer> get(ListNode<Integer> list)
	{
		validateInput(list);

		int size = getListSize(list);
		int middleElementPosition = size % 2 != 0 ? size / 2 : (size / 2) - 1;
		ListNode middleElement = list;
		for (int i = 0; i < middleElementPosition; i++)
		{
			middleElement = middleElement.getNext();
		}
		return middleElement;
	}

	private int getListSize(ListNode<Integer> list)
	{
		int size = 0;
		ListNode currentNode = list;
		while (currentNode != null)
		{
			size++;
			currentNode = currentNode.getNext();
		}
		return size;
	}

	/**
	 * Iterative solution to this problem. The complexity order of this algorithm in time and space
	 * terms is equivalent to the previous algorithm but the execution time is lower in this version.
	 * The key of this algorithm is based on use two pointers, going going two times faster trough
	 * the
	 * list during the iteration.
	 */
	public ListNode<Integer> get2(ListNode<Integer> list)
	{
		validateInput(list);

		ListNode<Integer> currentNode = list;
		ListNode<Integer> fastNode = list.getNext();
		while (fastNode != null && fastNode.getNext() != null)
		{
			currentNode = currentNode.getNext();
			fastNode = fastNode.getNext().getNext();
		}
		return currentNode;
	}

	private void validateInput(ListNode list)
	{
		if (list == null)
		{
			throw new IllegalArgumentException("You can't pass a null instance of list");
		}
	}
}
