package basic.other;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe 测试特殊运算符
 */
public class TestString
{
	public static void main(String[] args)
	{
	}

	@Test
	public void test1()
	{
		Pattern p = Pattern.compile("\\(.*\\)");
		Matcher m = p.matcher("1.2.0(23)");
		if (m.find())
		{
			System.out.println(m.start() + " " + m.end());
		}
	}

	@Test
	public void test2()
	{
		Pattern p = Pattern.compile("dispatch/afterhandle/(.*)/");
		Matcher m = p.matcher("http://200.200.6.22:8887/lxyisa/dispatch/afterhandle/5d5bcedd/main?name=val");
		if (m.find())
		{
			String group = m.group();
			System.out.println(group);
			System.out.println(m.start() + " " + m.end());
		}
	}

	@Test
	public void test3()
	{
		String url = "http://200.200.6.22:8887/lxyisa/dispatch/afterhandle/5d5bcedd/main?name=val";
		int i = url.indexOf("dispatch/afterhandle/");
		System.out.println(i);

		int beginIndex = i + "dispatch/afterhandle/".length();
		String substring = url.substring(beginIndex, beginIndex + 8);
		System.out.println(substring);
	}

}
