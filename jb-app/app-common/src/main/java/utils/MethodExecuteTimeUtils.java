package utils;

import java.lang.reflect.Method;

/**
 * This class is getting a method execute time and provide some other functions.
 */
public class MethodExecuteTimeUtils
{

	/**
	 * Get a method execute time using millisecond and cancel method's print return result and
	 * execute time.
	 *
	 * @param bean       The method is in this bean
	 * @param params     The parameter the method execute need
	 * @param methodName The name of the method
	 * @param types      The parameter type of the method
	 * @return The execute time of this method
	 * @throws Exception If <code>getMethod</code> or <code>invoke</code> fail.
	 */
	public static long getMethodExecuteTime(Object bean, Object[] params, String methodName, Class<?>[] types)
		throws Exception
	{
		return getMethodExecuteTime(bean, params, methodName, types, false, false);
	}

	/**
	 * Get a method execute time using millisecond and cancel print method's return result.
	 *
	 * @param bean               The method is in this bean
	 * @param params             The parameter the method execute need
	 * @param methodName         The name of the method
	 * @param types              The parameter type of the method
	 * @param isPrintExecutetime Whether print the execute time in console, true is print false not
	 * @return The execute time of this method
	 * @throws Exception If <code>getMethod</code> or <code>invoke</code> fail.
	 */
	public static long getMethodExecuteTime(Object bean, Object[] params, String methodName,
		Class<?>[] types, boolean isPrintExecutetime)
		throws Exception
	{
		return getMethodExecuteTime(bean, params, methodName, types, isPrintExecutetime, false);
	}

	/**
	 * Get a method execute time using millisecond and add some other service.
	 *
	 * @param bean               The method is in this bean
	 * @param params             The parameter the method execute need
	 * @param methodName         The name of the method
	 * @param types              The parameter type of the method
	 * @param isPrintExecutetime Whether print the execute time in console, true is print false not
	 * @param isViewMehtodResult Whether print the return result in console, true is print false not
	 * @return The execute time of this method
	 * @throws Exception If <code>getMethod</code> or <code>invoke</code> fail.
	 */
	public static long getMethodExecuteTime(Object bean, Object[] params, String methodName,
		Class<?>[] types, boolean isPrintExecutetime, boolean isViewMehtodResult)
		throws Exception
	{
		Class<?> clazz;
		long executeTime = -1L;
		boolean isAccessiable = false;
		Method method = null;
		if (bean instanceof Class<?>)
		{
			clazz = (Class<?>)bean;
		}
		else
		{
			clazz = bean.getClass();
		}
		try
		{
			if (types == null)
			{
				method = clazz.getDeclaredMethod(methodName);
			}
			else
			{
				method = clazz.getDeclaredMethod(methodName, types);
			}
			isAccessiable = method.isAccessible();
			if (!isAccessiable)
			{
				method.setAccessible(true);
			}

			if (isViewMehtodResult)
			{
				executeTime = getReturnMethodExecuteTime(bean, params, method);
			}
			else
			{
				executeTime = getMethodExecuteTime(bean, params, method);
			}
			method.setAccessible(isAccessiable);
			if (isPrintExecutetime)
			{
				printExecute(clazz, methodName, executeTime);
			}
		}
		catch (Exception e)
		{
			throw new Exception("excute method fail");
		}
		return executeTime;
	}

	/**
	 * Get a method execute time, use millisecond. We don't think the method whether has a return
	 * result
	 *
	 * @param bean   The method is in this bean
	 * @param params The parameters the method execute need
	 * @param method The Method entity
	 * @return The millisecond the method execute spend
	 * @throws Exception If the method invoke fail
	 */
	private static long getMethodExecuteTime(Object bean, Object[] params, Method method)
		throws Exception
	{
		long startTime = System.currentTimeMillis();
		method.invoke(bean, params);
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	/**
	 * Get a method execute time, use millisecond. The method must has a return result will input
	 * the return result in console ,If the method has not return result, please call
	 * <code>getMethodExecuteTime</code> method.
	 *
	 * @param bean   The method is in this bean
	 * @param params The parameters the method execute need
	 * @param method The Method entity
	 * @return The millisecond the method execute spend
	 * @throws Exception If the method invoke fail
	 */
	private static long getReturnMethodExecuteTime(Object bean, Object[] params, Method method)
		throws Exception
	{
		long startTime = System.currentTimeMillis();
		Object result = (Object)method.invoke(bean, params);
		long endTime = System.currentTimeMillis();
		if (result != null)
		{
			System.out.println("result input：" + result.toString());
		}
		else
		{
			System.out.println("Warning:" + bean.getClass().getName() + "." + method.getName()
				+ "has not return " + "result,please setting the isViewMehtodResult as false");
		}
		return endTime - startTime;
	}

	/**
	 * Print the execute time of method
	 *
	 * @param methodName The name of the method
	 * @param time       The execute of the method
	 */
	public static void printExecute(Class<?> clazz, String methodName, long time)
	{
		System.out.println(clazz.getName() + "." + methodName + " execute time: " + time);
	}
}
