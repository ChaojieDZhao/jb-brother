package basic.design.creatingpattern.simplefactory02;

/**
 * 抽象产品
 */
interface MeizuPhone
{
	/**
	 * 方法运行
	 */
	void run();
}

/**
 * @describe
 */
public class TestSimpleFactoryPattern
{
	public static void main(String[] args)
		throws Exception
	{
		Factory factory = new Factory();
		factory.produce("PRO5").run();
		factory.produce("PRO6").run();
	}
}

class PRO5 implements MeizuPhone
{
	@Override
	public void run()
	{
		System.out.println("我是一台PRO5");
	}
}

class PRO6 implements MeizuPhone
{
	@Override
	public void run()
	{
		System.out.println("我是一台PRO6");
	}
}

class Factory
{
	MeizuPhone produce(String product)
		throws Exception
	{
		if ("PRO5".equals(product))
		{
			return new PRO5();
		}
		else if ("PRO6".equals(product))
		{
			return new PRO6();
		}
		throw new Exception("No Such Class");
	}
}