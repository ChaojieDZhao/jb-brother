package basic.clazz.innerClass.InterfaceInner;

public class NestedInterfaceDemo implements Displayble.Message
{

	public static void main(String[] args)
	{

		Displayble.Message message = new NestedInterfaceDemo();
		message.msg();
	}

	@Override
	public void msg()
	{
		System.out.println("Hello Nested Interface");
	}
}
