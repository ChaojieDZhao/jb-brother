package algorithem.problem31;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindLongestConsecutiveSequenceTest
{

	private FindLongestConsecutiveSequence lcs;

	@Before
	public void setUp()
	{
		this.lcs = new FindLongestConsecutiveSequence();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysIterative()
	{
		lcs.findIterative(null);
	}

	@Test
	public void shouldReturnZeroIfTheArrayIsEmptyIterative()
	{
		int[] array = {};

		int sequenceLength = lcs.findIterative(array);

		assertEquals(0, sequenceLength);
	}

	@Test
	public void shouldReturnZeroIfThereIsNoAnyConsecutiveSequenceIterative()
	{
		int[] array = {6, 5, 4, 3, 2, 1};

		int sequenceLength = lcs.findIterative(array);

		assertEquals(0, sequenceLength);
	}

	@Test
	public void shouldReturnLengthArrayIfTheArrayIsOneConsecutiveSequenceIterative()
	{
		int[] array = {1, 2, 3, 4, 5, 6};

		int sequenceLength = lcs.findIterative(array);

		assertEquals(6, sequenceLength);
	}

	@Test
	public void shouldFindLongestConsecutiveSequenceIterative()
	{
		int[] array = {1, 3, 4, 5, 64, 4, 5, 6, 7, 8, 9, 98, -1, -2};

		int sequenceLength = lcs.findIterative(array);

		assertEquals(7, sequenceLength);
	}

	@Test
	public void shouldFindLongestConsecutiveSequenceIterativeSupportingEqualsConsecutiveNumbers()
	{
		int[] array = {1, 1, 1, 1, 3, 4, 5, 64, 4, 5, 6, 7, 8, 9, 98, -1, -2};

		int sequenceLength = lcs.findIterative(array);

		assertEquals(7, sequenceLength);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptNullArraysRecursive()
	{
		lcs.findRecursive(null);
	}

	@Test
	public void shouldReturnZeroIfTheArrayIsEmptyRecursive()
	{
		int[] array = {};

		int sequenceLength = lcs.findRecursive(array);

		assertEquals(0, sequenceLength);
	}

	@Test
	public void shouldReturnZeroIfThereIsNoAnyConsecutiveSequenceRecursive()
	{
		int[] array = {6, 5, 4, 3, 2, 1};

		int sequenceLength = lcs.findRecursive(array);

		assertEquals(0, sequenceLength);
	}

	@Test
	public void shouldReturnLengthArrayIfTheArrayIsOneConsecutiveSequenceRecursive()
	{
		int[] array = {1, 2, 3, 4, 5, 6};

		int sequenceLength = lcs.findRecursive(array);

		assertEquals(6, sequenceLength);
	}

	@Test
	public void shouldFindLongestConsecutiveSequenceRecursive()
	{
		int[] array = {1, 3, 4, 5, 64, 4, 5, 6, 7, 8, 9, 98, -1, -2};

		int sequenceLength = lcs.findRecursive(array);

		assertEquals(7, sequenceLength);
	}

	@Test
	public void shouldFindLongestConsecutiveSequenceRecursiveSupportingEqualsConsecutiveNumbers()
	{
		int[] array = {1, 1, 1, 1, 3, 4, 5, 64, 4, 5, 6, 7, 8, 9, 98, -1, -2};

		int sequenceLength = lcs.findRecursive(array);

		assertEquals(7, sequenceLength);
	}
}
