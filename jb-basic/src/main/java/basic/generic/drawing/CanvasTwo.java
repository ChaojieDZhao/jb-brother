package basic.generic.drawing;

import java.util.ArrayList;
import java.util.Collection;

interface Sink<T>
{
	void flush(T t);
}

public class CanvasTwo<T>
{

	public static void main(String[] args)
	{
		Object[] oa = new Object[100];
		Collection<Object> co = new ArrayList<Object>();

		// T 被推断为 Object
		fromArrayToCollection(oa, co);

		String[] sa = new String[100];
		Collection<String> cs = new ArrayList<String>();

		// T 被推断为 String
		fromArrayToCollection(sa, cs);

		// T 被推断为 Object
		fromArrayToCollection(sa, co);

		Integer[] ia = new Integer[100];
		Float[] fa = new Float[100];
		Number[] na = new Number[100];
		Collection<Number> cn = new ArrayList<Number>();

		// T 被推断为 Number
		fromArrayToCollection(ia, cn);

		// T 被推断为 Number
		fromArrayToCollection(fa, cn);

		// T 被推断为 Number
		fromArrayToCollection(na, cn);

		// T 被推断为 Object
		fromArrayToCollection(na, co);

		// 编译异常
		//fromArrayToCollection(na, cs);

		Sink<Object> s = null;
		Collection<String> cs2 = null;
		String str = writeAll(cs2, s);
	}

	static <T> void fromArrayToCollection(T[] a, Collection<T> c)
	{
		for (T o : a)
		{
			c.add(o); // Correct
		}
	}

	public static <F, T extends F> T writeAll(Collection<T> coll, Sink<F> snk)
	{
		T last = null;
		for (T t : coll)
		{
			last = t;
			snk.flush(last);
		}
		return last;
	}
}