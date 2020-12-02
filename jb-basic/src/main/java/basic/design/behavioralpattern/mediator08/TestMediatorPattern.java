package basic.design.behavioralpattern.mediator08;

interface IRenter
{
	public void rentHouseNotify(Mediator mediator);

	public void rentHourse();
}

/**
 * 中介者模式包装了一系列对象相互作用的方式，使得这些对象不必相互明显作用。从而使它们可以松散耦合。当某些对象之间的作用发生改变时，不会立即影响其他的一些对象之间的作用。保证这些作用可以彼此独立的变化。
 */
public class TestMediatorPattern
{
	public static void main(String[] args)
		throws java.lang.Exception
	{
		Mediator mediator = new Mediator();
		Renter01 renter01 = new Renter01();
		Renter02 renter02 = new Renter02();
		mediator.setRenter01(renter01).setRenter02(renter02);
		renter01.rentHouseNotify(mediator);
	}
}

class Mediator
{
	IRenter renter01;

	IRenter renter02;

	public Mediator setRenter01(Renter01 renter01)
	{
		renter01 = renter01;
		return this;
	}

	public Mediator setRenter02(Renter02 renter02)
	{
		renter02 = renter02;
		return this;
	}

	public void notifyOthers(Object obj)
	{
		if (obj instanceof Renter01)
		{
			System.out.println("renter02 收到");
		}
		else if (obj instanceof Renter02)
		{
			System.out.println("renter01 收到");
		}
		else
		{
			System.out.println("no class");
		}
	}
}

class Renter01 implements IRenter
{
	public void rentHouseNotify(Mediator mediator)
	{
		rentHourse();
		mediator.notifyOthers(this);
	}

	public void rentHourse()
	{
		System.out.println("renter01 rent a hourse");
	}
}

class Renter02 implements IRenter
{
	public void rentHouseNotify(Mediator mediator)
	{
		rentHourse();
		mediator.notifyOthers(this);
	}

	public void rentHourse()
	{
		System.out.println("renter02 rent a hourse");
	}
}