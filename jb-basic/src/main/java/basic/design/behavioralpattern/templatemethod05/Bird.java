package basic.design.behavioralpattern.templatemethod05;

public class Bird extends Animal
{

	public Bird()
	{
		System.out.println("小鸟登场：");
	}

	public void speak()
	{
		System.out.println("叽叽喳喳");
	}

	public void sleep()
	{
		System.out.println("小鸟睡在树上。");
	}

	public void move()
	{
		System.out.println("飞翔");
	}
}
 
