package basic.other;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestFeatures
{

	private static int[] nums = new int[] {2, 6, 8, 20, 10, 5, 8, 3};

	public static void main(String[] args)
	{
		System.out.println(Integer.valueOf("1001", 2));
		String test = ";";
		test.length();

	}

	@Test
	public void test0001()
	{
        /*System.out.println("2002".charAt(3) - '0' instanceof Integer);
        System.out.println("2002".charAt(3) instanceof Integer);
        System.out.println('0' instanceof Integer);*/
	}

	@Test
	public void test0002()
	{
		int a = 0;
		String.valueOf(0);
	}

	/**
	 * 冒泡排序 小到大
	 */
	@Test
	public void test0003()
	{
		int temp;
		for (int i = 0; i < nums.length; i++)
		{
			for (int j = i; j < nums.length; j++)
			{
				//如果前面的大于后面的
				if (nums[i] > nums[j])
				{
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		for (int i : nums)
		{
			sb.append(i).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	@Test
	public void test0004()
	{
		double[] doubles = {0, 2, 5, 6};
		double[] doubles2 = {0, 2, 5, 6};
		System.out.println(doubles == doubles2);

		System.out.println(doubles.equals(doubles2));

		System.out.println(Arrays.equals(doubles, doubles2));

		List<String> strings = Lists.newArrayList();
		strings.add("1");
		strings.add("2");
		strings.add("3");

		List<String> strings2 = Lists.newArrayList();
		strings2.add("1");
		strings2.add("2");
		strings2.add("3");
		System.out.println(strings == strings2);

		String[] _strings = {"1", "3", "2"};
		String[] _strings2 = {"1", "3", "2"};
		System.out.println(_strings == _strings2);
	}

	@Test
	public void test0005()
	{
		Double[] doubles = {0.1, 0.2, 0.5, 0.6};
		Double[] doubles2 = {0.1, 0.2, 0.5, 0.6};
		System.out.println(doubles == doubles2);

		System.out.println(doubles.equals(doubles2));
		System.out.println(Arrays.equals(doubles, doubles2));

		Double double1 = 0.1;
		Double double2 = 0.1;
		System.out.println(double1 == double2);
		System.out.println(double1.equals(double2));

		double double_1 = 0.1;
		double double_2 = 0.1;
		System.out.println(double_1 == double_2);
	}

	@Test
	public void test0006()
	{
		String[] arrays = {"2", "4", "5"};
	}
}

