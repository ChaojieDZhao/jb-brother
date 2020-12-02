package basic.design.structuralpattern.decorator11;

/**
 * 顾名思义，装饰模式就是给一个对象增加一些新的功能，而且是动态的，要求装饰对象和被装饰对象实现同一个接口
 */
interface IBulb
{
	/**
	 * 打开灯泡
	 */
	void turnOn();
}

public class TestDecoratorPattern
{
	public static void main(String[] args)
	{
		IBulb bulb = new Bulb();
		bulb.turnOn();
		BlueBulbDecorator bulbDecorator = new BlueBulbDecorator(bulb);
		bulbDecorator.turnOn();
		bulbDecorator = new YellowBulbDecorator(bulb);
		bulbDecorator.turnOn();
	}
}

class Bulb implements IBulb
{
	@Override
	public void turnOn()
	{
		System.out.println("turnOn the white light !!! ");
	}
}

abstract class AbstractBulbDecorator implements IBulb
{
	IBulb bulb;

	AbstractBulbDecorator(IBulb bulb)
	{
		this.bulb = bulb;
	}

	@Override
	public void turnOn()
	{
		bulb.turnOn();
	}
}

class BlueBulbDecorator extends AbstractBulbDecorator
{
	BlueBulbDecorator(IBulb bulb)
	{
		super(bulb);
	}

	@Override
	public void turnOn()
	{
		System.out.println("turnOn blue light");
		bulb.turnOn();
	}
}

class YellowBulbDecorator extends BlueBulbDecorator
{
	YellowBulbDecorator(IBulb bulb)
	{
		super(bulb);
	}

	@Override
	public void turnOn()
	{
		System.out.println("turnOn yellow light");
		bulb.turnOn();
	}
}