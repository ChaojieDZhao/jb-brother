package basic.keyword;

public class BreakTest001
{
	static int i = 0;

	public static void main(String[] args)
	{
		breakTest001();
		System.out.println("i am mid line ======");
		breakTest002();
	}

	static void breakTest001()
	{
		for (int j = 0; j < 2; j++)
		{
			for (int k = 0; k < 2; k++)
			{
				for (int l = 0; l < 2; l++)
				{
					System.out.println(j + " " + k + " " + l);
				}
			}
		}
	}

	static void breakTest002()
	{
		top:
		for (int j = 0; j < 2; j++)
		{
			for (int k = 0; k < 2; k++)
			{
				for (int l = 0; l < 2; l++)
				{
					System.out.println(j + " " + k + " " + l);
					break top;
				}
			}
		}
		System.out.println("i am breakTest002");
	}
}
