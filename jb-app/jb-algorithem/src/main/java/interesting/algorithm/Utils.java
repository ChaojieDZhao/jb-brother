package interesting.algorithm;

public class Utils
{

	public static Boolean[] getSameBooleanElementArray(Integer number, Boolean value)
	{
		Boolean[] booleans = new Boolean[number];
		for (int i = 0; i < number; i++)
		{
			booleans[i] = value;
		}
		return booleans;
	}

	public static Integer[] getSameIntegerElementArray(Integer number, Integer value)
	{
		Integer[] integers = new Integer[number];
		for (int i = 0; i < number; i++)
		{
			integers[i] = value;
		}
		return integers;
	}
}
