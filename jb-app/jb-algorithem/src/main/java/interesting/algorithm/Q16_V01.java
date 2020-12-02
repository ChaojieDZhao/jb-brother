package interesting.algorithm;

/**
 * @describe
 * @name Q16_V01
 */
public class Q16_V01
{

	private static final int maxLength = 500;

	private static int ways = 0;

	public static void main(String[] args)
	{
		for (int i = 1; i <= maxLength; i++)
		{
			if (i % 4 != 0)
			{
				continue;
			}
			else
			{
				satisfy(i);
			}
		}
		System.out.println(ways);
	}

	public static void satisfy(Integer length)
	{
		int square = length / 4;
		for (int i = 1; i < length / 2; i++)
		{
			for (int j = 1; j < length / 2; j++)
			{
				if (i == square || j == square || (length - 2 * j) % 2 != 0 || (length - 2 * i) % 2 != 0)
				{
					break;
				}
				else
				{
					int a = (length - 2 * j) / 2;
					int b = (length - 2 * i) / 2;
					if (b * i + a * j == square * square)
					{
						System.out.println(b + "*" + i + "   " + a + "*" + j + " = " + square + " *  " + square);
						ways++;
					}
				}
			}
		}
	}
}

