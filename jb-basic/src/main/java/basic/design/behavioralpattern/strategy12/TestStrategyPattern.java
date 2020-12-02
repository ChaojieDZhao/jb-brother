package basic.design.behavioralpattern.strategy12;

/**
 * 策略模式定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响到使用算法的客户。
 */
interface IBulb
{
	/**
	 * 打开灯泡
	 */
	void turnOn();
}

public class TestStrategyPattern
{
	public static void main(String[] args)
		throws java.lang.Exception
	{
		Client client = new Client();
		client.setBulb(new YellowBulb());
		client.open();
		client.setBulb(new BlueBulb());
		client.open();
	}
}

class Client
{
	IBulb bulb;

	public void setBulb(IBulb bulb)
	{
		this.bulb = bulb;
	}

	public void open()
	{
		bulb.turnOn();
	}
}

class BlueBulb implements IBulb
{
	@Override
	public void turnOn()
	{
		System.out.println("turnOn blue light");
	}
}

class YellowBulb implements IBulb
{
	@Override
	public void turnOn()
	{
		System.out.println("turnOn yellow light");
	}
}