package basic.exception;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Function:方法区内存溢出 1.8之后修改为元数据区
 */
public class MetaSpaceOOM
{

	public static void main(String[] args)
	{
		while (true)
		{
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(HeapOOM.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor()
			{
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
					throws Throwable
				{
					return methodProxy.invoke(o, objects);
				}
			});
			enhancer.create();
			System.out.println("HEHEDA");
		}
	}
}
