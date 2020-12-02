package basic.design.behavioralpattern.templatemethod05;

/**
 * 一次性实现一个算法的不变的部分，并将可变的行为留给子类来实现。
 * 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。
 * 这是Opdyke和Johnson所描述过的“重分解以一般化”的一个很好的例子。
 * 首先识别现有代码中的不同之处，并且将不同之处分离为新的操作。最后，用一个调用这些新的操作的模板方法来替换这些不同的代码。
 * 控制子类扩展。模板方法只在特定点调用“hook”操作，这样就只允许在这些点进行扩展。
 */
public class TestTemplateMethodPattern
{
	/**
	 * 具体实现交给子类处理，
	 * 核心方法就是模板方法（核心方法里面调用的方法都是子类的具体实现的方法）。
	 * 这样可以有效的约束和使用。
	 *
	 * @param args
	 */
	public static void main(String args[])
	{
		TestTemplateMethodPattern.action(new Human());
		TestTemplateMethodPattern.action(new Dog());
		TestTemplateMethodPattern.action(new Bird());
	}

	public static void action(Animal animal)
	{
		animal.action(animal.SPEAK);
		animal.action(animal.SLEEP);
		animal.action(animal.MOVE);
	}
}