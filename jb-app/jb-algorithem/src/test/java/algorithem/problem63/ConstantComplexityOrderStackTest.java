package algorithem.problem63;

import algorithem.problem63.ConstantComplexityOrderStack.Stack1;
import algorithem.problem63.ConstantComplexityOrderStack.Stack2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantComplexityOrderStackTest
{

	private Stack1 stack1;

	private Stack2 stack2;

	@Before
	public void setUp()
	{
		stack1 = new Stack1();
		stack2 = new Stack2();
	}

	@Test
	public void shouldReturnMinElementInTheStack1()
	{
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		stack1.push(-1);

		assertEquals(-1, stack1.getMin());
	}

	@Test
	public void shouldReturnMinElementInTheStack1EvenAfterModifyStackContent()
	{
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		stack1.push(-1);
		stack1.pop();

		assertEquals(1, stack1.getMin());
	}

	@Test
	public void shouldReturnMinElementInTheStack2()
	{
		stack2.push(1);
		stack2.push(2);
		stack2.push(3);
		stack2.push(-1);

		assertEquals(-1, stack2.getMin());
	}

	@Test
	public void shouldReturnMinElementInTheStack2EvenAfterModifyStackContent()
	{
		stack2.push(1);
		stack2.push(2);
		stack2.push(3);
		stack2.push(-1);
		stack2.pop();

		assertEquals(1, stack2.getMin());
	}
}
