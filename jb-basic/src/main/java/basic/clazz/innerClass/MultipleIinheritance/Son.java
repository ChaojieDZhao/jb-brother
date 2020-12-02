package basic.clazz.innerClass.MultipleIinheritance;

/**
 * 内部类的使用去实现多重继承。
 * 因为继承的父类只能有一个，所有只在一级继承里面不可能拥有两个父类共同的特性。
 * 不过可以使用内部类去实现多重继承。
 * 具体方案就是可以在类里面定义两个内部类分别去继承两个父类，然后该类调用自己的两个内部类实现父类方法的调用。
 */
public class Son
{

	public static void main(String[] args)
	{
		Son son = new Son();
		System.out.println("Son 的Strong：" + son.getStrong());
		System.out.println("Son 的kind：" + son.getKind());
	}

	public int getStrong()
	{
		return new Father_1().strong();
	}

	public int getKind()
	{
		return new Mother_1().kind();
	}

	/**
	 * 内部类继承Father类
	 */
	class Father_1 extends Father
	{
		public int strong()
		{
			return super.strong() + 1;
		}
	}

	/**
	 * 内部类继承Mother类
	 */
	class Mother_1 extends Mother
	{
		public int kind()
		{
			return super.kind() - 2;
		}
	}
}