package basic.keyword;

public class ContinueTest001
{
	static int i = 0;

	public static void main(String[] args)
	{
		continueTest001();
		System.out.println("i am mid line ======");
		continueTest002();
	}

	static void continueTest001()
	{
		top:
		while (i < 2)
		{
			for (int j = 0; j < 3; j++)
			{
				System.out.println(i);
				i++;
				continue top;
			}
		}
	}

	static void continueTest002()
	{
		top:
		for (int j = 0; j < 3; j++)
		{
			for (int k = 0; k < 2; k++)
			{
				for (int l = 0; l < 2; l++)
				{
					System.out.println(j + " " + k + " " + l);
					continue top;
				}
			}
		}
	}
}
