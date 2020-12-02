package basic.clazz.innerClass.MemberInnerClass;

/**
 * 成员内部类也是最普通的内部类，它是外围类的一个成员，所以他是可以无限制的访问外围类的所有 *成员属性和方法*，尽管是private的，
 * 但是外围类要访问内部类的成员属性和方法则需要通过内部类实例来访问。
 */
public class OuterClass
{
	private String str;

	public static void main(String[] args)
	{
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.getInnerClass();
		inner.innerDisplay();
	}

	public void outerDisplay()
	{
		System.out.println("outerClass...");
	}

	/*推荐使用getxxx()来获取成员内部类，尤其是该内部类的构造函数无参数时 */
	public InnerClass getInnerClass()
	{
		return new InnerClass();
	}

	public class InnerClass
	{
		public void innerDisplay()
		{
			//使用外围内的属性
			str = "chenssy...";
			System.out.println(str);
			//使用外围内的方法
			outerDisplay();
		}
	}
}