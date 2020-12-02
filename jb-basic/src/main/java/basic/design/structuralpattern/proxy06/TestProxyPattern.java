package basic.design.structuralpattern.proxy06;

/**
 * @describe
 */
public class TestProxyPattern
{
	public static void main(String args[])
	{
		AbstractObject obj = new ProxyObject();
		obj.method1();
		obj.method2();
		obj.method3();
	}
}