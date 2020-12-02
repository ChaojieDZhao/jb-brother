package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author zhacj
 * @describe 版本号工具类
 * @email alldios@139.com
 * @date 2019-01-29 18:29
 */
public class VersionUtils
{
	/**
	 * 结束版本枚举
	 */
	public final static int END_TYPE = 2;

	/**
	 * 开始版本枚举
	 */
	public final static int START_TYPE = 1;

	public static void main(String[] args)
	{
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("7");
		arrayList.add("7.0");
		arrayList.add("7.0.0");
		arrayList.add("2.3");
		arrayList.add("2.2.8");
		arrayList.add("7.0.0.0");
		arrayList.add("7.m.M");
		arrayList.add("7.m.2M.M");
		arrayList.add("7.m.M.2M");
		arrayList.add("7.1");
		arrayList.add("7.1.1");
		arrayList.add("7.1.1.1");
		arrayList.add("7.1.3.1");
		arrayList.add("7.y.y.1");
		arrayList.add("7.2.m.1");
		arrayList.add("7.2m.m.1");
		arrayList.add("7.3.w.1");
		arrayList.add("7.w.w.1");
		arrayList.add("7.ww.wm.1");
		arrayList.add("77.ww.wm.1");
		arrayList.add("777.ww.wm.1");
		arrayList.add("7777.ww.wm.1");

		for (String string : arrayList)
		{
			String versionNumber = getVersionNumber(string, START_TYPE);
			System.out.println("version:" + string + "    type=1:    " + versionNumber);
			String reverseVersion = reverseVersion(versionNumber, START_TYPE);
			System.out.println("version:" + string + "    reverse type=1:    " + reverseVersion);
			versionNumber = getVersionNumber(string, END_TYPE);
			System.out.println("version:" + string + "    type=2:    " + versionNumber);
			reverseVersion = reverseVersion(versionNumber, END_TYPE);
			System.out.println("version:" + string + "    reverse type=2:    " + reverseVersion);

		}
		long i = Long.valueOf(VersionUtils.getVersionNumber("2.2.8", 1)).longValue();
		System.out.println(i);

		String confrontTeamLog =

			"https://oes-image.miguvideo.com:1443/sports/uploadImages/2019/12/16/d0670b03-0d3f-4b44-9930-8cb83f664560.png|https://oes-image.miguvideo.com:1443/sports/uploadImages/2019/12/16/3d7c9d2a-3898-42a2-9ab9-8d5e77f002ec.png";

		if (StringUtils.isNotEmpty(confrontTeamLog))
		{
			String[] split = confrontTeamLog.split("\\|");
			if (split.length == 2)
			{
				System.out.println(split);
			}
		}

		String teamName = "切尔西,拜仁慕尼黑,";
		//如果队伍名称有逗号结尾, 则删除
		if (StringUtils.isNotEmpty(teamName) && teamName.endsWith(","))
		{
			teamName = teamName.substring(0, teamName.length() - 1);
		}
		System.out.println(teamName);

		System.out.println(trimNullToStr(null));
		System.out.println(trimNullToStr(""));
		System.out.println(trimNullToStr("234"));
	}

	public static String trimNullToStr(String source)
	{
		if (Objects.nonNull(source))
		{
			return source;
		}
		return "";
	}

	/**
	 * @describe 获取版本号
	 * @author zhacj
	 * @email alldios@139.com
	 * @date 2019-02-15 11:02
	 */
	public static String getVersionNumber(String version, int type)
	{
		if (StringUtils.isEmpty(version))
		{
			return null;
		}
		//移除字母
		version = version.replaceAll("[a-zA-Z]", "").replaceAll("\\.{2,4}", "\\.").replaceAll("\\.$", "");
		// 切分
		String[] split = version.split("\\.", -1);
		ArrayList<String> asList = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
		{
			if (i < split.length)
			{
				asList.add(split[i]);
			}
			else
			{
				asList.add(null);
			}
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < asList.size(); i++)
		{
			Integer a = 0;
			String s = asList.get(i);
			if (s != null && !"".equals(s))
			{
				a = Integer.valueOf(s);
			}

			if (i == 0)
			{
				sb.append(a);
			}
			else if (i == asList.size() - 1)
			{
				if (type == 2 && (s == null || "".equals(s)))
				{
					sb.append("9999");
				}
				else
				{
					sb.append(String.format("%04d", a));
				}
			}
			else
			{
				sb.append(String.format("%04d", a));
			}
		}
		return sb.toString();
	}

	/**
	 * @describe 根据组装字符串获取版本号
	 * @author zhacj
	 * @email alldios@139.com
	 * @date 2019-02-15 15:58
	 */
	public static String reverseVersion(String longVersion, int type)
	{
		if (StringUtils.isEmpty(longVersion))
		{
			return null;
		}
		String[] array = new String[4]; //新建一个长度为4的字符串数组
		String shortVersion = longVersion;
		//字符串长度为四段至少是13位
		if (12 < longVersion.length() && longVersion.length() <= 16)
		{
			//切分为四段
			array[0] = longVersion.substring(0, longVersion.length() - 12);
			array[1] = longVersion.substring(longVersion.length() - 12, longVersion.length() - 8);
			array[2] = longVersion.substring(longVersion.length() - 8, longVersion.length() - 4);
			array[3] = longVersion.substring(longVersion.length() - 4, longVersion.length());
			if (type == START_TYPE)
			{//起始版本号
				//去除多余的0
				for (int i = 1; i < 4; i++)
				{
					array[i] = array[i].replaceAll("^(0+)", "");
				}
				if (("".equals(array[3])) && ("".equals(array[2])) && ("".equals(array[1])))
				{//2 3 4段为补零
					shortVersion = array[0];
				}
				else if (("".equals(array[3])) && ("".equals(array[2])))
				{//3 4段为补零
					shortVersion = array[0] + "." + array[1];
				}
				else if ("".equals(array[3]))
				{//4段为补零
					if ("".equals(array[1]))//其中两个逗号之间补零
						array[1] = "0";
					shortVersion = array[0] + "." + array[1] + "." + array[2];
				}
				else
				{
					for (int i = 1; i < 3; i++)
					{//其中两个逗号之间补零
						if ("".equals(array[i]))
							array[i] = "0";
					}
					shortVersion = array[0] + "." + array[1] + "." + array[2] + "." + array[3];
				}
			}
			else if (type == END_TYPE)
			{//截止版本号
				//去除多余的0
				for (int i = 1; i < 4; i++)
				{
					array[i] = array[i].replaceAll("^(0+)", "");
				}
				if ((("".equals(array[3])) || ("9999".equals(array[3]))) && ("".equals(array[2])) &&
					("".equals(array[1])))
				{
					shortVersion = array[0];
				}
				else if ((("".equals(array[3])) || ("9999".equals(array[3]))) && ("".equals(array[2])))
				{
					shortVersion = array[0] + "." + array[1];
				}
				else if ((("".equals(array[3])) || ("9999".equals(array[3]))))
				{
					if ("".equals(array[1]))
						array[1] = "0";
					shortVersion = array[0] + "." + array[1] + "." + array[2];
				}
				else
				{
					for (int i = 1; i < 4; i++)
					{
						if ("".equals(array[i]))
							array[i] = "0";
					}
					shortVersion = array[0] + "." + array[1] + "." + array[2] + "." + array[3];
				}
			}
		}
		return shortVersion;
	}
}
