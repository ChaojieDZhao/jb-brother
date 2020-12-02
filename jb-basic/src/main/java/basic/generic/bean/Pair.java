package basic.generic.bean;

import java.lang.reflect.TypeVariable;

public class Pair<K extends CharSequence, V extends CharSequence>
{
	private K key;

	private V value;

	public Pair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	public static void main(String[] args)
	{
		Pair<String, String> pair = new Pair<>("123", "456");
		TypeVariable<? extends Class<? extends Pair>>[] typeParameters = pair.getClass().getTypeParameters();
		System.out.println(typeParameters[0].getBounds());
		System.out.println(((Class)typeParameters[0].getBounds()[0]).getSimpleName());

		int[] nums = new int[] {0, 1};
		int i = 0;
		System.out.println(nums[i++]);
		i = 0;
		System.out.println(nums[++i]);
	}

	public K getKey()
	{
		return key;
	}

	public void setKey(K key)
	{
		this.key = key;
	}

	public V getValue()
	{
		return value;
	}

	public void setValue(V value)
	{
		this.value = value;
	}
}