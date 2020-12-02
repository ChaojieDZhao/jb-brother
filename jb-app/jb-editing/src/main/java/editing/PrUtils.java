package editing;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PrUtils
{

	public static void main(String[] args)
	{
		System.out.println("isTotalScene：" + isTotalScene(" （咕run）（笑） ".trim()));
	}

	/**
	 * 是不是字幕序号
	 *
	 * @param line
	 * @return
	 */
	public static boolean isNumLine(String line)
	{
		return line.matches("[0-9]+");
	}

	/**
	 * 是不是时间码
	 *
	 * @param line
	 * @return
	 */
	public static boolean isTimeCode(String line)
	{
		return line.contains("-->");
	}

	/**
	 * 整行是不是全部的场景
	 *
	 * @param line
	 * @return
	 */
	public static boolean isTotalScene(String line)
	{
		String trim = line.trim();
		if (trim.startsWith("（") && trim.endsWith("）"))
		{
			return true;
		}
		return false;
	}

	public static boolean isTotalSceneByEnglish(String line)
	{
		String trim = line.trim();
		if (trim.startsWith("(") && trim.endsWith(")"))
		{
			return true;
		}
		return false;
	}

	public static boolean isScene(String s)
	{
		//定义左右括号的对应关系
		Map<Character, Character> bracket = new HashMap<>();
		bracket.put(')', '(');
		bracket.put('）', '（');
		bracket.put(']', '[');

		for (int i = 0; i < s.length(); i++)
		{
			Character temp = s.charAt(i);//先转换成字符
			//只要包括，则继续
			if (bracket.containsValue(temp) || bracket.containsKey(temp))
			{
				System.out.println("QNM");
				break;
			}
			if (i == s.length() - 1)
			{
				System.out.println("QNM");
				return false;
			}
			continue;
		}

		Stack stack = new Stack();

		for (int i = 0; i < s.length(); i++)
		{

			Character temp = s.charAt(i);//先转换成字符
			//是否为左括号
			if (bracket.containsValue(temp))
			{

				stack.push(temp);
				//是否为右括号
			}
			else if (bracket.containsKey(temp))
			{
				if (stack.isEmpty())
				{
					return false;
				}
				//若左右括号匹配
				if (stack.peek() == bracket.get(temp) || stack.peek().equals(bracket.get(temp)))
				{
					stack.pop();
				}
				else
				{
					return false;
				}
			}

		}
		return stack.isEmpty() ? true : false;

	}

	//是不是字幕
	public static boolean isSubtitle(String line)
	{
		line = EncodeUtil.removeUTF8BOM(line);
		System.out.println("进入判断是否是字幕，判断内容：" + line + "  ，长度：" + line.length());
		if (line.matches("[0-9]+") || line.trim().length() == 0)
		{
			System.out.println(line + " --字幕码，和空行--");
			return false;
		}
		//更新为最新的时间code
		if (line.contains("-->"))
		{
			System.out.println(line + "  --时间Code--");
			return false;
		}
		//如果场景，则不输出
		if (isScene(line))
		{
			return false;
		}
		return true;
	}

	//是不是字幕，包含场景
	public static boolean isSubtitleIncludeScene(String line)
	{
		System.out.println("进入判断是否是字幕，判断内容：" + line + "  ，长度：" + line.length());
		if (line.matches("[0-9]+") || line.trim().length() == 0)
		{
			System.out.println(line + " --字幕码，和空行--");
			return false;
		}
		//更新为最新的时间code
		if (line.contains("-->"))
		{
			System.out.println(line + "  --时间Code--");
			return false;
		}
		return true;
	}
}
