package basic.exception;

import com.google.common.collect.Lists;

import java.util.List;

public class HeapOOM
{

	public static void main(String[] args)
	{
		List<String> list = Lists.newArrayList();
		while (true)
		{
			list.add("1");
		}
	}
}

