package basic.other;

import org.junit.Test;

/**
 * @describe 测试一些表达式能力
 */
public class TestExpressions
{
	@Test
	public void test1()
	{
		int[] ints = new int[] {1, 2, 3};
		int i = 0;
		System.out.println(ints[i++]);
		i = 0;
		System.out.println(ints[++i]);
	}

}
