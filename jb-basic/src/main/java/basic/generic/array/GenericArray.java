package basic.generic.array;

public class GenericArray<T>
{
	private Object[] values;

	public GenericArray(int count)
	{

		values = new Object[count];

	}

	public static void main(String[] args)
	{

		GenericArray<String> generic = new GenericArray(10);

		generic.setValue("wenwei1", 0);
		generic.setValue("wenwei2", 1);

		System.out.println(generic.getValue(0));
		System.out.println(generic.getValue(1));
		// 会报错
		String[] content = generic.getValues();

	}

	public void setValue(T t, int position)
	{

		values[position] = t;
	}

	public T getValue(int position)
	{

		return (T)values[position];
	}

	//注意此句代码调用会出错
	public T[] getValues()
	{
		return (T[])values;
	}
}


