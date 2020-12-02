package interesting.algorithm;

/**
 * @describe
 * @name Q15_V02
 */
public class Q15_V02
{

	private static final int[] steps = new int[] {1, 2, 3, 4};

	private static final int step = 10;

	private static Integer[] position = Utils.getSameIntegerElementArray(step + 1, 0);

	public static void main(String[] args)
	{
		position[step] = 1;
		int count = 0;
		//移动的次数
		for (int i = 0; i < step; i++)
		{
			//移动的位置数量
			for (int j = 0; j < step + 1; j++)
			{
				for (int oneStep :
					steps)
				{
					if (oneStep > j)
					{
						break;
					}
					else
					{
						//position[10] = position[9] + position[8] + position[7] + position[6]
						//position[9] = position[8] + position[7] + position[6] + position[5]
						//position[8] = position[7] + position[6] + position[5] + position[4]
						position[j - oneStep] = position[j] + position[j - oneStep];
					}
				}
				position[j] = 0;
			}
			count = i % 2 == 1 ? count + position[0] : count;
		}
		System.out.println(count);
	}

}

