package basic.design.creatingpattern.singleton01;

/**
 * @describe 单例模式可以使得一个类只有一个对象实例，能够减少频繁创建对象的时间和空间开销。
 */
public class SimpleSingleton
{
	/**
	 * 可能同时有两个线程A和B同时执行到 if 条件判断语句，
	 * A判断singleton为空准备执行 step_1 时让出了CPU时间片，
	 * B也判断singleton为空，接着执行 step_1，
	 * 此时创建了一个实例对象；
	 * A获取了CPU时间片后接着执行 step_1，
	 * 也创建了实例对象，这就导致多个单例对象的情况.
	 * 解决的方法也很简单，使用synchronized关键字，如Singleton02
	 */
	private static SimpleSingleton instance;

	private SimpleSingleton()
	{
	}

	public static SimpleSingleton getIntance()
	{
		if (instance == null)
		{
			//step_1
			instance = new SimpleSingleton();
		}
		return instance;
	}
}

class Singleton02
{
	/**
	 * 这样解决了多线程并发的问题，
	 * 但是却带来了效率问题：我们的目的是只创建一个实例，
	 * 即 step_1 处代码只会执行一次，也正是这个地方才需要同步，
	 * 该方案同步了整个方法，每次获取实例的时候都要使用同步方法进行非空验证，没有效率。
	 * 后面创建了实例之后，singleton非空就会直接返回对象引用，
	 * 而不用每次都在同步代码块中进行非空验证。那么可以考虑只对 step_1 处进行同步：
	 */
	private static Singleton02 instance;

	private Singleton02()
	{
	}

	public static synchronized Singleton02 getIntance()
	{
		if (instance == null)
		{
			instance = new Singleton02();  //step_1
		}
		return instance;
	}
}

class Singleton03
{
	/**
	 * 同样的问题，即多个线程同时执行到条件判断语句时，
	 * 会创建多个实例。问题在于当一个线程创建一个实例之后，
	 * singleton就不再为空了，
	 * 但是后续的线程并没有做第二次非空检查。
	 * 那么很明显，在同步代码块中应该再次做检查，也就是所谓的双重检测：
	 */
	private static Singleton03 singleton;

	private Singleton03()
	{
	}

	public static Singleton03 getInstance()
	{
		if (singleton == null)
		{
			synchronized (Singleton03.class)
			{
				//step_1
				singleton = new Singleton03();
			}
		}
		return singleton;
	}
}

class SingletonDoubleCheck
{
	private static SingletonDoubleCheck singleton;

	private SingletonDoubleCheck()
	{
	}

	public static SingletonDoubleCheck getInstance()
	{
		if (singleton == null)
		{
			synchronized (SingletonDoubleCheck.class)
			{
				if (singleton == null)
				{
					singleton = new SingletonDoubleCheck();   //step_1
				}
			}
		}
		return singleton;
	}
}

/**
 * 静态内部类单例方式是Singleton类被装载了，instance不一定被初始化。
 * 因为SingletonHolder类没有被主动使用，
 * 只有显式通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。
 * 想象一下，如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，
 * 因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化instance显然是不合适的。
 */
class SingletonInnerClass
{
	private SingletonInnerClass()
	{
	}

	public static final SingletonInnerClass getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
	}
}