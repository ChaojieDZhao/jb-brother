package basic.other;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author zhacj
 * @describe 版本号工具类
 * @email alldios@139.com
 * @date 2019-01-29 18:29
 */
public class Test
{
	public static void main(String[] args)
	{

		ArrayList<String> strs = Lists.newArrayList();

		strs.add("my name is awesome");
		strs.add("123");
		strs.add("123");
		strs.add("456");

		ArrayList<Object> objects = Lists.newArrayList();

		HashSet<String> duplicateRemovalSet = new HashSet();
		for (int i = 0; i < strs.size(); i++)
		{
			//如果已经有了，则抛弃掉
			if (duplicateRemovalSet.contains(strs.get(i)))
			{
				continue;
			}
			objects.add(strs.get(i));
			duplicateRemovalSet.add(strs.get(i));
		}
		System.out.println(objects);
	}

}

