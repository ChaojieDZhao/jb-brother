package basic.design.creatingpattern.prototype07;

/**
 * 一个原型类，只需要实现Cloneable接口，覆写clone方法，此处clone方法可以改成任意的名称.
 * 因为Cloneable接口是个空接口，你可以任意定义实现类的方法名，如cloneA或者cloneB，因为此处的重点是super.clone()这句话，
 * super.clone()调用的是Object的clone()方法，而在Object类中，clone()是native的。
 */

//使用 java 自带的支持
public class APITestUse
{
	public static void main(String args[])
		throws CloneNotSupportedException
	{
		MyObject myObject = new MyObject();
		myObject.i = 500;
		MyObject myObjectClone = (MyObject)myObject.clone();
		System.out.println(myObjectClone.i);
	}
}

class MyObject implements Cloneable
{
	int i;

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}
}