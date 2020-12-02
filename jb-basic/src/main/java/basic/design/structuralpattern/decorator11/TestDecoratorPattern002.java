package basic.design.structuralpattern.decorator11;

/**
 * 装饰器模式能够动态地为对象添加功能，是从一个对象外部来给对象添加功能，
 * 相当于改变了对象的外观。从外部使用系统的角度看，就不再是使用原始的那个对象了，而是使用被一系列装饰器装饰过后的对象。
 * 这样就能够灵活的改变一个对象的功能，只要动态组合的装饰器发生了改变，那么最终所得到的对象的功能就发生了改变。
 * 另一个好处是装饰器功能的复用，可以给一个对象多次增加同一个装饰器，也可以用同一个装饰器来装饰不同的对象。而且符合面向对象设计中的一条基本规则："尽量使用对象组合，而不是对象继承"
 * <p>
 * <p>
 * 比继承更灵活。继承是静态的，而且一旦继承所有子类都有一样的功能。而装饰模式采用把功能分离到每个装饰器中，然后通过对象组合的方式，在运行时动态的组合功能，每个被装饰的对象最终有哪些功能，是由运行期动态组合的功能决定的
 * 更容易复用功能。有利于装饰器功能的复用，可以给一个对象多次增加同一个装饰器，也可以用同一个装饰器来装饰不同的对象
 * 简化高层定义。装饰模式可以通过组合装饰器方式给对象增添任意多的功能，因此在进行高层定义的时候，只需要定义最基本的功能就可以了，需要的时候结合相应装饰器完成需要的功能
 */
public class TestDecoratorPattern002
{
	public static void main(String[] args)
	{
		Beverage coffee = new Espresso();//一杯浓缩咖啡
		System.out.println(coffee.getDescription() + " : $" + coffee.cost());
		coffee = new Milk(coffee);
		System.out.println(coffee.getDescription() + " : $" + coffee.cost());
		coffee = new Sugar(coffee);
		System.out.println(coffee.getDescription() + " : $" + coffee.cost());
		//+双份糖的深焙咖啡
		coffee = new DarkRoast();
		coffee = new Sugar(new Sugar(coffee));
		System.out.println(coffee.getDescription() + " : $" + coffee.cost());
	}
}

//饮料-抽象构件
abstract class Beverage
{
	String description = "";

	public String getDescription()
	{
		return description;
	}

	public abstract double cost();
}

//浓缩咖啡-具体构件1
class Espresso extends Beverage
{
	public Espresso()
	{
		description = "浓缩咖啡(Espresso)";
	}

	@Override
	public double cost()
	{
		return 1.99;
	}
}

//深焙咖啡-具体构件2
class DarkRoast extends Beverage
{
	public DarkRoast()
	{
		description = "深焙咖啡(DarkRoast)";
	}

	@Override
	public double cost()
	{
		return 2.99;
	}
}

//调料-抽象装饰器
abstract class CondimentDecorator extends Beverage
{
	@Override
	public abstract String getDescription();
}

//糖调料-具体装饰器
class Sugar extends CondimentDecorator
{
	Beverage beverage;

	public Sugar(Beverage beverage)
	{
		this.beverage = beverage;
	}

	@Override
	public String getDescription()
	{
		return beverage.getDescription() + " + 糖 ";
	}

	@Override
	public double cost()
	{
		return 0.20 + beverage.cost();
	}
}

//牛奶-具体装饰器
class Milk extends CondimentDecorator
{
	Beverage beverage;

	public Milk(Beverage beverage)
	{
		this.beverage = beverage;
	}

	@Override
	public String getDescription()
	{
		return beverage.getDescription() + " + 牛奶 ";
	}

	@Override
	public double cost()
	{
		return 0.30 + beverage.cost();
	}
}


