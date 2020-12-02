package tips.tool;

import java.util.Scanner;

public class Convert
{
	public static void main(String[] args)
	{
		while (true)
		{
			String line = scanner("转换语句");
			String replaceAll = line.replaceAll("\\/", "_").replaceAll("\\:", "_");
			System.out.println(replaceAll);
		}
	}

	public static String scanner(String tip)
	{
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext())
		{
			String ipt = scanner.next();
			return ipt;
		}
		return null;
	}
}
