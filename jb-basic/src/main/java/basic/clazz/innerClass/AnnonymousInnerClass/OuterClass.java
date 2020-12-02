package basic.clazz.innerClass.AnnonymousInnerClass;

interface InnerClass
{
	int getNumber();
}

/**
 * 匿名内部类
 */
public class OuterClass
{
	public static void main(String[] args)
	{
		OuterClass out = new OuterClass();
		InnerClass inner = out.getInnerClass(2, "chenssy");
		System.out.println(inner.getNumber());
	}

	public InnerClass getInnerClass(final int num, String str2)
	{
		return new InnerClass()
		{
			int number = num + 3;

			public int getNumber()
			{
				return number;
			}
		};        /* 注意：分号不能省 */
	}
}