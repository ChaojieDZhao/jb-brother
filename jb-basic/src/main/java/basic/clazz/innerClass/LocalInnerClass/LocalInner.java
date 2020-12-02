package basic.clazz.innerClass.LocalInnerClass;

/**
 * 局部内部类
 * Local inner class cannot be invoke from outside the method
 * Local inner class connot be access non-final local variable
 */
public class LocalInner
{

	private int data = 30; // instance Variable

	public static void main(String[] args)
	{

		LocalInner localInner = new LocalInner();

		localInner.display();
	}

	void display()
	{

		int value = 20; // local Variable but non-final

		class Local
		{

			void msg()
			{

				System.out.println("Value is " + data);

				System.out.println("Value is " + value);  //TODO 是不是java8可以访问非fianl变量了？
			}

		} // end Loaca class

		Local local = new Local();
		local.msg();

	}// end display method
}

