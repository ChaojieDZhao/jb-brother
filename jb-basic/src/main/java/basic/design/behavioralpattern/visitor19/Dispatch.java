package basic.design.behavioralpattern.visitor19;

//演示java的静态分派和动态分派
//结果会输出：Collection
//所以重载的分派是根据静态类型进行的

/**
 * 编译器在重载时是通过参数的静态类型而不是实际类型作为判定的依据。并且静态类型在编译期可知，因此，编译阶段，Javac编译器会根据参数的静态类型决定使用哪个重载版本。
 */
public class Dispatch
{
	public static void main(String args[])
	{
		FatherClass child = new ChildClass();
		new Dispatch().print(child);
		child.print();
	}

	void print(FatherClass c)
	{
		System.out.print("父类");
	}

	void print(ChildClass c)
	{
		System.out.print("子类");
	}
}

class FatherClass
{
	void print()
	{
		System.out.println("父类");
	}
}

class ChildClass extends FatherClass
{
	void print()
	{
		System.out.print("子类");
	}
}