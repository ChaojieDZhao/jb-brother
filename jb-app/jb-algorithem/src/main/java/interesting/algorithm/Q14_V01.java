package interesting.algorithm;

/**
 * @describe 一个标准的深度优先搜索
 * @name Q14_V01
 */
public class Q14_V01
{

	private static final String[] country = new String[] {"Brazil", "Croatia", "Mexico", "Cameroon",
		"Spain", "Netherlands", "Chile", "Australia",
		"Colombia", "Greece", "Cote d'Ivoire", "Japan",
		"Uruguay", "Costa Rica", "England", "Italy",
		"Switzerland", "Ecuador", "France", "Honduras",
		"Argentina", "Bosnia and Herzegovina", "Iran",
		"Nigeria", "Germany", "Portugal", "Ghana",
		"USA", "Belgium", "Algeria", "Russia",
		"Korea Republic"};

	private static Boolean[] isUsed = Utils.getSameBooleanElementArray(country.length, false);

	private static Integer maxDepth = 0;

	public static void main(String[] args)
	{
		for (int i = 0; i < country.length; i++)
		{
			isUsed[i] = true;
			search(country[i], 1);
			isUsed[i] = false;
		}
		System.out.println(maxDepth);
	}

	private static Character getLastChar(String value)
	{
		Character c = value.charAt(value.length() - 1);
		return c;
	}

	private static Character getFirstChar(String value)
	{
		Character c = value.charAt(0);
		return c;
	}

	private static Integer search(String previous, Integer depth)
	{
		boolean isLast = true;
		for (int i = 0; i < country.length; i++)
		{
			if (getLastChar(country[i].toUpperCase()).equals(getFirstChar(previous.toUpperCase())))
			{
				if (!isUsed[i])
				{
					isLast = false;
					isUsed[i] = true;
					search(country[i], depth + 1);
					isUsed[i] = false;
				}
			}
		}
		if (isLast)
		{
			maxDepth = maxDepth >= depth ? maxDepth : depth;
		}
		return maxDepth;
	}
}

