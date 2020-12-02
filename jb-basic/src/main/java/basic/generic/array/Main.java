package basic.generic.array;

import org.junit.Test;

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
	}

	@Test
	public void test1()
	{
		String[] strArray = new String[20];
		Object[] objArray = strArray;
		//java.lang.ArrayStoreException: java.lang.Integer
		objArray[0] = new Integer(1);   //运行时会检查加入数组的对象的类型
	}

	@Test
	public void test2()
	{
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(2);
		Object obj = intList;
		ArrayList<String> strList = (ArrayList<String>)obj;
		//Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
		strList.add("2");
		System.out.print(strList.get(0));
		System.out.print(strList.get(1));
	}

	@Test
	public void test3()
	{
		ArrayList list = new ArrayList();
		list.add(new Pair());
		list.add(5);
		Pair p = (Pair)list.get(0);
		p.info();
		Integer number = (Integer)list.get(1);
		System.out.println(number);
	}

	@Test
	public void test4()
	{
		ArrayList list = new ArrayList();
		list.add(new Pair());
		list.add(5);
		Pair p = (Pair)list.get(1);
		p.info();
	}
}

class Pair<T>
{
	public void info()
	{
		System.out.println("I am Pair");
	}
}


