package interesting.algorithm;

/**
 * @describe
 * @name Q15_V01
 */
public class Q15_V01
{

	private static final int[] steps = new int[] {1, 2, 3, 4};

	public static void main(String[] args)
	{
		int stepA = 0, stepB = 10;
		int step = step(stepA, stepB, 0);
		System.out.println(step);
	}

	public static int step(int stepA, int stepB, int way)
	{
		if (stepA == stepB)
		{
			return 1;
		}
		else if (stepA > stepB)
		{
			return 0;
		}
		else
		{
			for (int i = 0; i < steps.length; i++)
			{

				for (int j = 0; j < steps.length; j++)
				{
					way += step(stepA + steps[i], stepB - steps[j], 0);
				}
			}
			return way;
		}
	}
}

