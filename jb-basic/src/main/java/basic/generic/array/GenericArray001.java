package basic.generic.array;

import java.lang.reflect.Array;

class GenericArray001<T>
{
	private T[] values;

	public GenericArray001(Class<T> type, int length)
	{
		values = (T[])Array.newInstance(type, length);
	}

	public static void main(String[] args)
	{

		GenericArray001<String> generic = new GenericArray001<String>(String.class, 10);

		generic.setValue("wenwei1", 0);
		generic.setValue("wenwei2", 1);

		System.out.println(generic.getValue(0));
		System.out.println(generic.getValue(1));

		String[] content = generic.getValues();
		System.out.println(content[0]);
	}

	public void setValue(T t, int position)
	{

		values[position] = t;
	}

	public T getValue(int position)
	{

		return (T)values[position];
	}

	public T[] getValues()
	{

		return values;
	}
}   
