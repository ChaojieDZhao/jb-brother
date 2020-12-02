package algorithem.problem64;

import algorithem.linkedlist.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetTheElementInTheMiddleOfTheListTest
{

	private GetTheElementInTheMiddleOfTheList getTheElementInTheMiddleOfTheList;

	@Before
	public void setUp()
	{
		getTheElementInTheMiddleOfTheList = new GetTheElementInTheMiddleOfTheList();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullListInstances()
	{
		getTheElementInTheMiddleOfTheList.get(null);
	}

	@Test
	public void shouldReturnTheElementInTheMiddleOfAListWithAnEvenNumberOfElements()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode<Integer> middleElement = getTheElementInTheMiddleOfTheList.get(list);

		assertEquals(Integer.valueOf(2), middleElement.getData());
	}

	@Test
	public void shouldReturnTheElementInTheMiddleOfTheListWithAnOddNumberOfElements()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3, 4});

		ListNode<Integer> middleElement = getTheElementInTheMiddleOfTheList.get(list);

		assertEquals(Integer.valueOf(2), middleElement.getData());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullListInstances2()
	{
		getTheElementInTheMiddleOfTheList.get2(null);
	}

	@Test
	public void shouldReturnTheElementInTheMiddleOfAListWithAnEvenNumberOfElements2()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3});

		ListNode<Integer> middleElement = getTheElementInTheMiddleOfTheList.get2(list);

		assertEquals(Integer.valueOf(2), middleElement.getData());
	}

	@Test
	public void shouldReturnTheElementInTheMiddleOfTheListWithAnOddNumberOfElements2()
	{
		ListNode<Integer> list = createList(new int[] {1, 2, 3, 4});

		ListNode<Integer> middleElement = getTheElementInTheMiddleOfTheList.get2(list);

		assertEquals(Integer.valueOf(2), middleElement.getData());
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
