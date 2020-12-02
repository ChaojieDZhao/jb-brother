package basic.clazz.innerClass.StaticInnerClass;

public class StaticInnerClass
{
	private static double static_age = 1.0;

	private double age = 1.0;

	/**
	 * 内部类不允许有static修饰的方法
	 */
    /*class InnerClass6 {
        public static double getAge() {
            return static_age;
        }
    }*/
	public static void main(String[] args)
	{
		StaticInnerClass annonymousDemo003 = new StaticInnerClass();
		System.out.println(StaticInnerClass.InnerClass4.getAge());
		InnerClass2 innerClass2 = annonymousDemo003.new InnerClass2();
		System.out.println(innerClass2.getAge());
		InnerClass3 innerClass3 = annonymousDemo003.new InnerClass3();
		System.out.println(innerClass3.getAge());
		System.out.println(StaticInnerClass.InnerClass4.getAge());
	}

	public static class InnerClass4
	{
		public static double getAge()
		{
			return static_age;
		}
	}

	abstract class InnerClass
	{
		public abstract double getAge();
	}

	public class InnerClass2
	{
		public double getAge()
		{
			return age;
		}
	}

    /*private class InnerClass3 {
        public double getAge() {
            return age;
        }
    }*/

	/**
	 * 1.
	 * <p>
	 * you couldn't guarantee that the inner class existed when you attempted to call the static method. ”
	 * <p>
	 * 如果内部类没有static的话，就需要实例化内部类才能调用，说明非static的内部类不是自动跟随外部类加载的，而是被实例化的时候才会加载。
	 * 而static的语义，就是外部类能直接通过内部类名来访问内部类中的static方法，而非static的内部类又是不会自动加载的，所以这时候内部类也要static，否则会前后冲突。
	 * <p>
	 * 2.
	 * <p>
	 * 非static的内部类，在外部类加载的时候，并不会加载它，所以它里面不能有静态变量或者静态方法。
	 * 1、static类型的属性和方法，在类加载的时候就会存在于内存中。
	 * 2、要使用某个类的static属性或者方法，那么这个类必须要加载到jvm中。
	 * * 基于以上两点，可以看出，如果一个非static的内部类如果具有static的属性或者方法，那么就会出现一种情况：内部类未加载，但是却试图在内存中创建static的属性和方法，这当然是错误的。原因：类还不存在，但却希望操作它的属性和方法。
	 */

	private class InnerClass3
	{
		public double getAge()
		{
			return age;
		}
	}

}
