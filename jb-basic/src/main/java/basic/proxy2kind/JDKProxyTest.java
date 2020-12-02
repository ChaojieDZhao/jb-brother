package basic.proxy2kind;

import basic.proxy2kind.jdk.CustomizeHandle;
import basic.proxy2kind.jdk.ISubject;
import basic.proxy2kind.jdk.impl.ISubjectImpl;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class JDKProxyTest
{

	@Test
	public void test()
		throws InterruptedException
	{
		CustomizeHandle handle = new CustomizeHandle(ISubjectImpl.class);
		ISubject subject =
			(ISubject)Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(), new Class[] {ISubject.class}, handle);
		System.out.println(subject.getClass().getName());
		subject.execute();
		subject.print("你好");
	}

	public void clazzTest()
	{
		byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
			"$Proxy1", new Class[] {ISubject.class}, 1);
		try
		{
			FileOutputStream out = new FileOutputStream("d://Users/chenjie/Documents/$Proxy1.class");
			out.write(proxyClassFile);
			out.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
