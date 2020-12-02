package basic.generic.drawing;

import com.google.common.collect.Lists;

import java.util.List;

public class Canvas<T>
{
	static
	{
		//T t3; //编译报错
	}

	//static T t; //编译报错
	T t1;

	List[] lsa = new List[10];

	//List<String>[] lsa = new List<String>[10];
	Object o = lsa;

	Object[] oa = (Object[])o;

	public static void main(String[] args)
	{
		Canvas canvas = new Canvas();
		List<Shape> shapes = Lists.newArrayList();
		canvas.drawAll(shapes);
	}

	public static void drawAll(List<Shape> shapes)
	{
		//List<T> ls = new ArrayList<T>();
	}

	public void draw(Shape s)
	{
		s.draw(this);
	}
}