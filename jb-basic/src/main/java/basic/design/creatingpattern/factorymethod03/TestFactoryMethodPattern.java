package basic.design.creatingpattern.factorymethod03;

interface IFactory
{
	/**
	 * 生产
	 *
	 * @return
	 */
	IPhone produce();
}

interface IPhone
{

	/**
	 * 方法运行
	 */
	void execute();
}

/**
 * @describe
 */
public class TestFactoryMethodPattern
{
	public static void main(String[] args)
		throws Exception
	{
		IFactory vivoPhoneFactory = new VivoPhoneFactory();
		IFactory oppoPhoneFactory = new OppoPhoneFactory();
		vivoPhoneFactory.produce().execute();
		oppoPhoneFactory.produce().execute();

	}
}

class VivoPhoneFactory implements IFactory
{
	@Override
	public IPhone produce()
	{
		return new VivoPhone();
	}
}

class OppoPhoneFactory implements IFactory
{
	@Override
	public IPhone produce()
	{
		return new OppoPhone();
	}
}

class VivoPhone implements IPhone
{
	@Override
	public void execute()
	{
		System.out.println("i am vivo Phone");
	}
}

class OppoPhone implements IPhone
{
	@Override
	public void execute()
	{
		System.out.println("i am oppo Phone");
	}
}

